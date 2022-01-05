package com.shudazy.tool.rmi.service;

import java.io.Serializable;
import java.rmi.Remote;

/**
 *
 * @author Bing D. Yee
 * @since 2021/12/13
 */
public interface EvilService extends Remote, Serializable {

    void hello();

}
