package org.wdn.guick

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class GreetTask extends DefaultTask {

    def text

    @TaskAction
    def run() {
        println "Hello!"
    }
}
