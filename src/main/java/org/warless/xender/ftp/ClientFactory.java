package org.warless.xender.ftp;

import org.warless.xender.constant.ClientConfig;

/**
 * Xender
 *
 * @author : Noa Swartz
 * @version : 1.0.0
 * @date : 2019/5/10
 */
public interface ClientFactory {

    Client createClient(ClientConfig config);

}
