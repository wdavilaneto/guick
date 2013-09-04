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

        } catch (RuntimeException e) {
            e.printStackTrace("Erro ao configurar Guick Plugin", e)
        }

        GuickCoreTask task = project.tasks.create("installWebArtifacts", InstallWebArtifactsTask)
        task.text = "Instal Guick Web Artifacts"
        task.description = 'Guick Plugin Instal  Web Artifacts '

        task = project.tasks.create("migrateToDb", MigrateToDbTask)
        task.text = "Migrate to Database"
        task.description = 'Guick Plugin'

        task = project.tasks.create("migrateToDomain", MigrateToDomain)
        task.text = "Migrate to Domain (code)"
        task.description = 'Guick Plugin'

    }

    WarPluginConvention getWarConvention(Project project) {
        project.convention.getPlugin(WarPluginConvention)
    }
}
