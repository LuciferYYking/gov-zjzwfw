package com.unittec.poseidon.gradle;

import io.spring.gradle.dependencymanagement.DependencyManagementPlugin;
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.springframework.boot.gradle.plugin.SpringBootPlugin;
import org.springframework.boot.gradle.util.VersionExtractor;

/**
 * @author Bing D. Yee
 * @since v1.0.0 2021/03/28
 */
public class PoseidonBootPlugin implements Plugin<Project> {

    static final String BOM_COORDINATES = "com.unittec.poseidon:poseidon-dependencies:";

    @Override
    public void apply(Project project) {
        project.getPlugins().apply(DependencyManagementPlugin.class);
        project.getPlugins().apply(SpringBootPlugin.class);
        String version = VersionExtractor.forClass(PoseidonBootPlugin.class);
        project.getExtensions().findByType(DependencyManagementExtension.class)
                .imports((importsHandler) -> importsHandler.mavenBom(PoseidonBootPlugin.BOM_COORDINATES + version));
        System.err.println("---------------------------");
    }

}
