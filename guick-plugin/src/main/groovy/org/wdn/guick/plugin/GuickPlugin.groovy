package org.wdn.guick.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.GroovyPlugin
import org.gradle.api.tasks.JavaExec

/**
 * A {@link Plugin}
 */
class GuickPlugin extends GroovyPlugin {

    JavaExec toDomain, toDb, webArtifacts

    @Override
    void apply(Project project) {

        project.plugins.apply(GroovyPlugin)
        project.configurations.create("guick").setVisible(false).setTransitive(true).setDescription('The Guick libraries to be used for this project.')

        try {
            String pack = "org.wdn.default"
            if (project?.group != null) {
                pack = project.group.toString().toLowerCase()
            }

            if (project?.name != null) {
                pack = pack + "." + project.name?.replaceAll("-", "").replaceAll(" ", "").toLowerCase()
            }

        } catch (RuntimeException e) {
            e.printStackTrace("Erro ao configurar Guick Plugin", e)
        }

        def allClasspath = project.sourceSets.main.runtimeClasspath + project.buildscript.configurations.classpath

        webArtifacts = project.tasks.create("installWebArtifacts", JavaExec)
        webArtifacts.main = "org.wdn.guick.plugin.GuickMain"
        webArtifacts.classpath += allClasspath
        webArtifacts.args 'installWebArtifacts'

        toDb = project.tasks.create("migrateToDb", JavaExec)
        toDb.dependsOn("classes")
        toDb.main = "org.wdn.guick.plugin.GuickMain"
        toDb.classpath += allClasspath
        toDb.args 'migrateToDb'

        toDomain = project.tasks.create("migrateToDomain", JavaExec)
        toDomain.dependsOn("classes")
        toDomain.main = "org.wdn.guick.plugin.GuickMain"
        toDomain.classpath += allClasspath
        toDomain.args 'migrateToDomain'

        toDomain = project.tasks.create("generateCrud", JavaExec)
        toDomain.dependsOn("classes")
        toDomain.main = "org.wdn.guick.plugin.GuickMain"
        toDomain.classpath += allClasspath
        toDomain.args 'generateCrud'

    }
}

