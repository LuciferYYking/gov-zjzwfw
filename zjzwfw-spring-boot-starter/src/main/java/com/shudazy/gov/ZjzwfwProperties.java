package com.shudazy.gov;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "zjzwfw")
public class ZjzwfwProperties {

    /** 浙里办配置 */
    private ClientConfig app;
    /** IRS 配置 */
    private ClientConfig irs;
    /** 浙政钉配置 */
    private ClientConfig ding;

}
