package org.wdn.guick

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.wdn.guick.core.DslArchitecture
import org.wdn.guick.model.Project

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 10:32 PM
 * To change this template use File | Settings | File Templates.
 */
class Main {

    private static final DEFAULT_PROJECT_ROOT = "../wdavilaneto"

    public static void main(String[] args) {

        Main main = new Main();

        try {
            main.execute(args == [] ? DEFAULT_PROJECT_ROOT : args[0])
        } catch (RuntimeException e) {
            e.printStackTrace()
        }
    }

    public execute(String path = DEFAULT_PROJECT_ROOT) {
        ExpandoMetaClass.disableGlobally()
        // Initializing Spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.wdn.guick");
        context.refresh();

        // Initializing project path(like done by maven3 plugin or gradle plugin)
        Project project = (Project) context.getBean("project");
        project.initialize(path)

        // Lets run an example ...
        DslArchitecture guick = (DslArchitecture) context.getBean("dslArchitecture");
//        guick.runEngine("installPom")
//        guick.runEngine("installGradle")
//        guick.runEngine("persistence")
        guick.runEngine("installWebArtifacts")
        guick.runEngine("domain")
    }

}

