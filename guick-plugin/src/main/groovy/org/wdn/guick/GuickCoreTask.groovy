package org.wdn.guick

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.wdn.guick.core.DslArchitecture
import org.wdn.guick.model.Project

/**
 * Created with IntelliJ IDEA.
 * User: y1z5
 * Date: 03/09/13
 * Time: 16:59
 * To change this template use File | Settings | File Templates.
 */
abstract class GuickCoreTask  extends DefaultTask {

    abstract onExecute();

    DslArchitecture guickEngine;

    @TaskAction
    def run() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.wdn.guick");
        context.refresh();

        Project project = (Project) context.getBean("project");
        project.initialize();

        // Lets run an example ...
        guickEngine = (DslArchitecture) context.getBean("dslArchitecture");
        onExecute()
    }
}
