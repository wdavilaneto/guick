package org.wdn.guick.plugin

import org.gradle.api.Project
import org.gradle.api.tasks.JavaExec
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static org.junit.Assert.assertTrue

/**
 * Created with IntelliJ IDEA.
 * User: wdavilaneto
 * Date: 04/09/13
 * Time: 13:00
 * To change this template use File | Settings | File Templates.
 */
class GuickPluginTest {

//    @Test
    void testAddsMigrateToDbTask(){
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'guick'
        assertTrue (project.tasks.migrateToDb instanceof JavaExec)
    }

//    @Test
    void testMigrateToDbTask(){
        ProjectBuilder builder = new ProjectBuilder()
        builder.withProjectDir(new File("../wdavilaneto")).withName("wdavilaneto")
        Project project = builder.build()
        project.apply plugin: 'guick'
        project.plugins.getPlugin(GuickPlugin).toDb.classpath.each {
            println it
        }
        project.plugins.getPlugin(GuickPlugin).toDb.bootstrapClasspath.each{
            println it
        }
    }
}
