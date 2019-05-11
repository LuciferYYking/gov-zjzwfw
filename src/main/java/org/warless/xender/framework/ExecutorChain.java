package org.warless.xender.framework;

/**
 * ExecutorChain
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/11
 */
public interface ExecutorChain {

    void execute(ExecutorConfig config);

    default ExecutorChain setExecutor(Executor executor) {
        return this;
    }
    default ExecutorChain setNextChain(ExecutorChain nextChain) {
        return this;
    }

}
