package org.wdn.guick

import groovy.transform.CompileStatic
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.wdn.guick.common.ResourceReader
import org.wdn.guick.dsl.DslArchitecture

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 10:32 PM
 * To change this template use File | Settings | File Templates.
 */
class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.wdn.guick");
        context.refresh();
        DslArchitecture guick = (DslArchitecture) context.getBean("dslArchitecture");
        guick.runEngine("domain")
    }


}
