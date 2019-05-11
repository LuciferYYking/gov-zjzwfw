package org.warless.xender.framework.executors;

import org.warless.xender.framework.Executor;
import org.warless.xender.framework.ExecutorChain;
import org.warless.xender.framework.ExecutorConfig;

/**
 * FTPExecutor
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/11
 */
public class FTPExecutor implements Executor {
    @Override
    public void execute(ExecutorConfig config, ExecutorChain chain) {
        System.err.println("FTPExecutor execute()...");
        chain.execute(config);
    }
}