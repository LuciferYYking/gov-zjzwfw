package org.warless.xender.service;

/**
 * XenderService
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/11
 */
public interface XenderService {

    String upload(String file);

    String download(String file);

}
