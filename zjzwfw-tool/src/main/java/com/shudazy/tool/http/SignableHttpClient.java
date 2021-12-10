package com.shudazy.tool.http;

import com.shudazy.tool.exception.ServiceInvokeException;
import com.shudazy.tool.http.request.BaseRequest;
import com.shudazy.tool.http.sign.SignStrategy;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 支持签名的Http Client
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public class SignableHttpClient {

    private CloseableHttpClient httpClient;
    private IdleConnectionMonitorThread idleConnectionMonitorThread;
    private String baseUri;
    private long idletime;
    private int validateAfterInactivity;
    private Integer timeout;
    private HttpRoutePlanner routePlanner;
    private boolean inited;
    private final List<SignStrategy> signStrategyList;

    public static SignableHttpClient getInstance() {
        return FactoryHolder.EXECUTABLE_CLIENT;
    }

    private SignableHttpClient() {
        this.idletime = 30L;
        this.validateAfterInactivity = 60000;
        this.inited = false;
        this.signStrategyList = new ArrayList<>();
    }

    public void init() {
        if (!this.inited) {
            ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
            LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", plainsf).register("https", sslsf).build();
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(800);
            cm.setDefaultMaxPerRoute(400);
            cm.setValidateAfterInactivity(this.validateAfterInactivity);
            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            if (null != this.getRoutePlanner()) {
                httpClientBuilder.setRoutePlanner(this.getRoutePlanner());
            }
            this.httpClient = httpClientBuilder.setConnectionManager(cm).setConnectionManagerShared(true).build();
            this.idleConnectionMonitorThread = new SignableHttpClient.IdleConnectionMonitorThread(cm, this.idletime);
            this.idleConnectionMonitorThread.start();
            this.inited = true;
        }
    }

    public void registerSignStrategy(SignStrategy signStrategy) {
        this.signStrategyList.add(signStrategy);
    }

    public String execute(BaseRequest request) {
        String response;
        try {
            for (SignStrategy signStrategy : this.signStrategyList) {
                if (request.support(signStrategy)) {
                    signStrategy.signRequest(request);
                    break;
                }
            }
            HttpUriRequest httpRequest = request.createHttpRequest();
            response = this.httpClient.execute(httpRequest, FactoryHolder.STRING_RESPONSE_HANDLER);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceInvokeException(e.getMessage());
        }
        return response;
    }

    public void destroy() {
        if (this.idleConnectionMonitorThread != null) {
            this.idleConnectionMonitorThread.shutdown();

            try {
                this.idleConnectionMonitorThread.join();
            } catch (InterruptedException ignored) {}
        }

        if (this.httpClient != null) {
            try {
                this.httpClient.close();
            } catch (IOException ignored) {}
        }

    }

    public Integer getTimeout() {
        return this.timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public CloseableHttpClient getHttpClient() {
        return this.httpClient;
    }

    public void setBaseUri(String uri) {
        this.baseUri = uri;
    }

    public String getBaseUri() {
        return this.baseUri;
    }

    public long getIdletime() {
        return this.idletime;
    }

    public void setIdletime(long idletime) {
        this.idletime = idletime;
    }

    public HttpRoutePlanner getRoutePlanner() {
        return this.routePlanner;
    }

    public void setRoutePlanner(HttpRoutePlanner routePlanner) {
        this.routePlanner = routePlanner;
    }

    public int getValidateAfterInactivity() {
        return this.validateAfterInactivity;
    }

    public void setValidateAfterInactivity(int validateAfterInactivity) {
        this.validateAfterInactivity = validateAfterInactivity;
    }

    private static class FactoryHolder {
        public static final SignableHttpClient EXECUTABLE_CLIENT = new SignableHttpClient();
        public static final SignableHttpClient.StringResponseHandler STRING_RESPONSE_HANDLER;

        private FactoryHolder() {
        }

        static {
            STRING_RESPONSE_HANDLER = new SignableHttpClient.StringResponseHandler(Consts.UTF_8.name());
        }
    }

    public static class StringResponseHandler implements ResponseHandler<String> {

        private final String encode;

        public StringResponseHandler(String encode) {
            this.encode = encode;
        }

        @Override
        public String handleResponse(HttpResponse response) throws IOException {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            String responseBody = entity == null ? null : EntityUtils.toString(entity, this.encode);
            if (statusLine.getStatusCode() < 300 && statusLine.getStatusCode() != 203) {
                return responseBody;
            } else {
                EntityUtils.consume(entity);
                throw new HttpResponseException(statusLine.getStatusCode(), responseBody);
            }
        }

    }

    public static class IdleConnectionMonitorThread extends Thread {
        private final HttpClientConnectionManager connMgr;
        private final long idletime;
        private volatile boolean shutdown;

        public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr, long idletime) {
            this.connMgr = connMgr;
            this.idletime = idletime;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    if (!this.shutdown) {
                        synchronized(this) {
                            this.wait(2000L);
                            this.connMgr.closeExpiredConnections();
                            this.connMgr.closeIdleConnections(this.idletime, TimeUnit.SECONDS);
                            continue;
                        }
                    }
                } catch (InterruptedException ignored) { }
                return;
            }
        }

        public void shutdown() {
            this.shutdown = true;
            synchronized(this) {
                this.notifyAll();
            }
        }
    }

}
