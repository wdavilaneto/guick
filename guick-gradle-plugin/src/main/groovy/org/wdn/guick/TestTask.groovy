package org.wdn.guick

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class TestTask extends DefaultTask {

    def destination

    File getDestination() {
        project.file(destination)
    }

    @TaskAction
    def greet() {
        def file = getDestination()
        file.parentFile.mkdirs()
        file.write "Hello!"
    }
}
