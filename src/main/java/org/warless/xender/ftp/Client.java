package org.warless.xender.ftp;


/**
 * Xender
 *
 * @author : Noa Swartz
 * @version : 1.0.0
 * @date : 2019/5/10
 */
public interface Client {

    void upload(String src, String dest);

    void download(String src, String dest);

    void close();

}
