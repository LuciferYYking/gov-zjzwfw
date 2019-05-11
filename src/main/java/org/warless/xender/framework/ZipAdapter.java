package org.warless.xender.framework;

import java.io.IOException;

/**
 * Xender
 *
 * @author : Noa Swartz
 * @version : 1.0.0
 * @date : 2019/5/8
 */
public interface ZipAdapter {

    /**
     *  zip
     *
     * @param src source path
     * @param dest dest path
     * @throws IOException e
     */
    void compress(String src, String dest) throws IOException;

    /**
     *  unzip
     *
     * @param src source path
     * @param dest dest path
     * @throws IOException e
     */
    void decompress(String src, String dest) throws IOException;

}
