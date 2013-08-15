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

    private static final DEFAULT_PROJECT_ROOT = "../myapp"

    public static void main(String[] args) {

        Main main = new Main();

        try {
            main.execute(args == [] ? DEFAULT_PROJECT_ROOT : args[0])
        } catch (RuntimeException e) {
            e.printStackTrace()
        }


    }

    public execute(String path = DEFAULT_PROJECT_ROOT) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.wdn.guick");
        context.refresh();

        Project project = (Project) context.getBean("project");
        project.initialize(path)

        DslArchitecture guick = (DslArchitecture) context.getBean("dslArchitecture");
        guick.runEngine("domain")
    }


}
