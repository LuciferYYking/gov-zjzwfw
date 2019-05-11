package org.warless.xender.framework;

import org.warless.xender.framework.constant.ClientConfig;

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
