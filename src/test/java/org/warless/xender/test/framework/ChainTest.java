package org.warless.xender.test.framework;

import org.junit.Test;
import org.warless.xender.framework.*;
import org.warless.xender.framework.executors.CryptoExecutor;
import org.warless.xender.framework.executors.FTPExecutor;

/**
 * ChainTest
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/11
 */
public class ChainTest {

    @Test
    public void test() {
        ExecutorChain chain = new XenderExecutorChain();
        chain.setExecutor(new FTPExecutor())
                .setNextChain(new XenderExecutorChain()
                        .setExecutor(new CryptoExecutor()));
        chain.execute(null);
    }

}
