package org.wdn.guick.plugin

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
class GuickMain implements Runnable {

    AnnotationConfigApplicationContext context
    DslArchitecture guick
    Project project
    String target

    GuickMain() {
        context = new AnnotationConfigApplicationContext();
        context.scan("org.wdn.guick");
        context.refresh();
        guick = (DslArchitecture) context.getBean("dslArchitecture");
        project = context.getBean("project");
    }

    public static void main(String[] args) {
        ExpandoMetaClass.disableGlobally()
        try {
            GuickMain main = new GuickMain();
            main.setTarget(args[0])
            if (args.size() >= 2) {
                main.project.initialize(args[1])
            } else {
                main.project.initialize()
            }
            main.run()
        } catch (RuntimeException e) {
            e.printStackTrace()
        }
    }

    public void run() {
        guick.runEngine(target)
    }

}

