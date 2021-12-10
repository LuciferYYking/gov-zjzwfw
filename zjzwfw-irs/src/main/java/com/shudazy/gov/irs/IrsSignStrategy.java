package com.shudazy.gov.irs;

import com.shudazy.tool.base.DigestHelper;
import com.shudazy.tool.http.request.BaseRequest;
import com.shudazy.tool.http.sign.SignStrategy;

/**
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public class IrsSignStrategy extends SignStrategy {

    public IrsSignStrategy(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    @Override
    public void signRequest(BaseRequest request) {
        String requestTime = String.valueOf(System.currentTimeMillis());
        request.addParameter("requestTime", requestTime)
                .addParameter("appKey", this.accessKey)
                .addParameter("sign", DigestHelper.md5hex(this.accessKey + this.secretKey + requestTime));
    }

}
