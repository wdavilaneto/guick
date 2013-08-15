package org.wdn.guick

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.WarPlugin
import org.gradle.api.plugins.WarPluginConvention

/**
 * A {@link Plugin} which extends the {@link WarPlugin}
 */
class GuickPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.plugins.apply(WarPlugin)

        project.configurations.create("guick").setVisible(false).setTransitive(true)
                .setDescription('The Guick libraries to be used for this project.')

        GuickPluginConventions conventions = new GuickPluginConventions()
        project.convention.plugins.guick = conventions

        try {
            String pack = conventions.basePackage
            if (project?.group != null) {
                pack = project.group.toString().toLowerCase()
            }

            if (project?.name != null) {
                pack = pack + "." + project.name?.replaceAll("-", "").replaceAll(" ", "").toLowerCase()
            }

            project.tasks.withType(GreetTask).whenTaskAdded { GreetTask testTask ->
//                testTask.conventionMapping.map("basePackage") {pack}
//                testTask.conventionMapping.map("modelPackage") {conventions.modelPackage}
//                testTask.conventionMapping.map("modelPackage") {conventions.servicePackage}
//                testTask.conventionMapping.map("controllerPackage") {conventions.controllerPackage}
//                testTask.conventionMapping.map("views") {conventions.views}
//                testTask.conventionMapping.map('webAppClasspath') { project.tasks.getByName(WarPlugin.WAR_TASK_NAME).classpath }
//                testTask.conventionMapping.map('webAppSourceDirectory') { getWarConvention(project).webAppDir }
//                testTask.conventionMapping.map('classesDirectory') { project.sourceSets.main.output.classesDir }
            }

        } catch (RuntimeException e) {
            e.printStackTrace("Erro ao configurar Guick Plugin", e)
        }

        GreetTask greetTask = project.tasks.create("greet", GreetTask)
        greetTask.text = "hellow world plugin"
        greetTask.description = 'Guick Plugin Test Task'
        greetTask.group = WarPlugin.WEB_APP_GROUP

    }

    WarPluginConvention getWarConvention(Project project) {
        project.convention.getPlugin(WarPluginConvention)
    }
}
