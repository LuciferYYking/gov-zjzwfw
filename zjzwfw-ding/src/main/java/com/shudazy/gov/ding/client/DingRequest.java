package com.shudazy.gov.ding.client;


/**
 * description:
 *
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public interface DingRequest<T> {

    /**
     * pass
     *
     * @param dingClient
     * @return
     */
    T execute(DingClient dingClient);

}
