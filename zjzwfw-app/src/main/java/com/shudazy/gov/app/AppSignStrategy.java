package com.shudazy.gov.app;

import com.shudazy.tool.base.DigestHelper;
import com.shudazy.tool.http.request.BaseRequest;
import com.shudazy.tool.http.sign.SignStrategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public class AppSignStrategy extends SignStrategy {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public AppSignStrategy(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    @Override
    public void signRequest(BaseRequest request) {
        String time = FORMATTER.format(LocalDateTime.now());
        request.addParameter("servicecode", this.accessKey)
                .addParameter("datatype", "json")
                .addParameter("time", time)
                .addParameter("sign", DigestHelper.md5hex(this.accessKey + this.secretKey + time));
    }

}
