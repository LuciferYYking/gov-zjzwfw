package com.shudazy.gov;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
@Getter
@Setter
@ToString
public class ClientConfig {

    public static final String PREFIX = "zjzwfw";

    public static final String ENABLE_APP = "enable-app";

    public static final String ENABLE_IRS = "enable-irs";

    public static final String ENABLE_DING = "enable-ding";


    private String accessKey;

    private String secretKey;

    private String domainName;

    private String protocol;

    private String authUrl;

    private String baseUrl;

}
