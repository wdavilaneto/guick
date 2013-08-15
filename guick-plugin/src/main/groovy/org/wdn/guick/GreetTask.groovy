package org.wdn.guick

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class GreetTask extends DefaultTask {

    def text
    def destination

    File getDestination() {
        project.file(destination)
    }

    @TaskAction
    def run() {
        def file = getDestination()
        file.parentFile.mkdirs()
        file.write "Hello!"
    }
}
