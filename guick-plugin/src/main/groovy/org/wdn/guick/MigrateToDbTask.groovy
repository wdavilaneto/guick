package org.wdn.guick

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created with IntelliJ IDEA.
 * User: y1z5
 * Date: 03/09/13
 * Time: 16:51
 * To change this template use File | Settings | File Templates.
 */
class MigrateToDbTask extends GuickCoreTask {

    def text

    @Override
    def onExecute() {
        project.get
        generator.classpath = getClasspath()
        guickEngine.runEngine("migrateToDb");
    }

}
