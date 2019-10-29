package org.wdn.guick

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.wdn.guick.core.DslArchitecture
import org.wdn.guick.model.Project

class GuickAppliction {

    private static final GUICK_CONTEXT_CLASSPATH = "org.wdn.guick"
    private static final DSL_ARCHITECTURE_BEAN_NAME = "dslArchitecture";
    private static final PROJECT_BEAN_NAME = "project";

    // Spring Application Context
    AnnotationConfigApplicationContext context

    DslArchitecture dslArchitecture
    Project project
    String target

    GuickAppliction() {
        // Spring context initialization
        context = new AnnotationConfigApplicationContext()
        context.scan(GUICK_CONTEXT_CLASSPATH)
        context.refresh()
        dslArchitecture = (DslArchitecture) context.getBean(DSL_ARCHITECTURE_BEAN_NAME)
        project = (Project)context.getBean(PROJECT_BEAN_NAME)
    }

    def initialize(String path) {
        project.initialize(path)
    }

    def setTarget(String lvalue) {
        target = lvalue
        return this
    }

    def run() {
        dslArchitecture.runEngine(target)
    }

}
