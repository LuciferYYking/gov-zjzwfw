package org.warless.xender.framework;


import org.warless.xender.framework.executors.*;

/**
 * XenderExecutorChain
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/11
 */
public class XenderExecutorChain implements ExecutorChain {

    protected Executor executor;
    protected ExecutorChain nextChain;

    public XenderExecutorChain() {

    }


    public Executor getExecutor() {
        return executor;
    }



    @Override
    public void execute(ExecutorConfig config) {
        executor.execute(config, nextChain);
    }

    @Override
    public ExecutorChain setExecutor(Executor executor) {
        this.executor = executor;
        return this;
    }

    public ExecutorChain getNextChain() {
        return nextChain;
    }

    @Override
    public ExecutorChain setNextChain(ExecutorChain nextChain) {
        this.nextChain = nextChain;
        return this;
    }
}
