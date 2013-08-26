package org.wdn.guick.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.wdn.guick.core.DslArchitecture;
import org.wdn.guick.model.Project;

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/22/13
 * Time: 9:55 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class GuickCoreMojo extends AbstractMojo {

    protected DslArchitecture guickEngine;

    public GuickCoreMojo(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.wdn.guick");
        context.refresh();

        Project project = (Project) context.getBean("project");
        project.initialize();

        // Lets run an example ...
        guickEngine = (DslArchitecture) context.getBean("dslArchitecture");

    }

}
