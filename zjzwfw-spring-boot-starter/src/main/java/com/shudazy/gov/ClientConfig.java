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

    private String accessKey;

    private String secretKey;

    private String domainName;

    private String protocol;

    private String authUrl;

    private String baseUrl;

}
