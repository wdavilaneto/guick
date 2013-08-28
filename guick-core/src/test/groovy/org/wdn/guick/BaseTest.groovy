package org.wdn.guick

import org.hibernate.cfg.Configuration
import org.junit.Before
import org.junit.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.GenericXmlApplicationContext
import org.wdn.guick.model.Project

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/28/13
 * Time: 12:02 AM
 * To change this template use File | Settings | File Templates.
 */
class BaseTest {

    static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    @Before
    void setUp() {
        context.scan("org.wdn.guick");
        context.refresh();
    }

    @Test
    void test() {
        // Initializing project path(like done by maven3 plugin or gradle plugin)
        Project project = (Project) context.getBean("project");
        project.initialize("../wdavilaneto")

        Configuration configuration = new Configuration()

        GenericXmlApplicationContext clientContext = new GenericXmlApplicationContext();
        clientContext.load("classpath*:application.xml" ,"classpath*:datasource.xml" )

        println clientContext.getBean("dataSource")


        println "test _> ${project.group}"
        assert true
    }


}

