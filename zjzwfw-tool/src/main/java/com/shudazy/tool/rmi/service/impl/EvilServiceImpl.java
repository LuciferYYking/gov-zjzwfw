package com.shudazy.tool.rmi.service.impl;

import com.shudazy.tool.rmi.service.EvilService;

/**
 * @author Bing D. Yee
 * @since 2021/12/13
 */
public class EvilServiceImpl implements EvilService {

    @Override
    public void hello() {
        System.err.println("Hello Apache Log4j CVE....");
    }

}
