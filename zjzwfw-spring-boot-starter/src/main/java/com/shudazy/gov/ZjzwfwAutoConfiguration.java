package com.shudazy.gov;

import com.shudazy.gov.app.client.AppClient;
import com.shudazy.gov.ding.client.DingClient;
import com.shudazy.gov.irs.client.IrsClient;
import com.shudazy.tool.http.SignableHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
@Configuration
@EnableConfigurationProperties(ZjzwfwProperties.class)
public class ZjzwfwAutoConfiguration {

    @Bean
    public SignableHttpClient signableHttpClient() {
        SignableHttpClient instance = SignableHttpClient.getInstance();
        instance.init();
        return instance;
    }

    @Bean
//    @ConditionalOnProperty(name = "zjzwfw.app")
    public AppClient appClient(ZjzwfwProperties properties, SignableHttpClient httpClient) {
        ClientConfig config = properties.getApp();
        return new AppClient(config.getAuthUrl(), config.getAccessKey(), config.getSecretKey(), httpClient);
    }

    @Bean
//    @ConditionalOnProperty(name = "zjzwfw.irs")
    public IrsClient irsClient(ZjzwfwProperties properties, SignableHttpClient httpClient) {
        ClientConfig config = properties.getIrs();
        return new IrsClient(config.getBaseUrl(), config.getAccessKey(), config.getSecretKey(), httpClient);
    }

    @Bean
//    @ConditionalOnProperty(name = "zjzwfw.ding")
    public DingClient dingClient(ZjzwfwProperties properties) {
        ClientConfig config = properties.getDing();
        return new DingClient(config.getAccessKey(), config.getSecretKey(), config.getDomainName(), config.getProtocol());
    }

}
