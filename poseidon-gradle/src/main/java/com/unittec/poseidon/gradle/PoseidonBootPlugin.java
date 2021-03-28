package com.unittec.poseidon.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author Bing D. Yee
 * @since v1.0.0 2021/03/28
 */
public class PoseidonBootPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        System.err.println("---------------------------");
    }

}
