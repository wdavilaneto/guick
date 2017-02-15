package org.wdn.guick

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.wdn.guick.core.DslArchitecture
import org.wdn.guick.loader.Json
import org.wdn.guick.model.Project

/**
 * Created with IntelliJ IDEA.
 * User: wdavilaneto
 * Date: 9/25/13
 * Time: 6:05 PM
 * To change this template use File | Settings | File Templates.
 */
class Main {

        private static final GUICK_CONTEXT_CLASSPATH = "org.wdn.guick"

    AnnotationConfigApplicationContext context
    DslArchitecture guick
    Project project
    String target

    Main() {
        context = new AnnotationConfigApplicationContext()
        context.scan(GUICK_CONTEXT_CLASSPATH)
        context.refresh()
        guick = (DslArchitecture) context.getBean("dslArchitecture")
        project = context.getBean("project")
    }

    public static void main(String[] args) {
        ExpandoMetaClass.disableGlobally()
        try {
            Main main = new Main()
            main.project.initialize("../mprj-gsi-ind")
            main.setTarget("stage/create-webapp").run()
            main.setTarget("stage/crud").run()
//            main.project.persist()
        } catch (RuntimeException e) {
            e.printStackTrace()
        }
    }

    public void run() {
        guick.runEngine(target)
    }

    public Main setTarget(String lvalue) {
        target = lvalue
        return this
    }

}

//main.setTarget("stage/domain")
