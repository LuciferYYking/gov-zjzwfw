package org.warless.xender.framework;

/**
 * Executor
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/8
 */
public interface Executor {

    void execute(ExecutorConfig config, ExecutorChain chain);

}
