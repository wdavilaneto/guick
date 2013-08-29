/*
 * Esse arquivo pertence a Petrobras e nao pode ser utilizado fora
 * dessa empresa sem previa autorizacao.
 */

package org.wdn.guick.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * The class GuickMigrateToDb
 * @goal migrate-to-db
 * @phase process-sources
 */
public class GuickMigrateToDb extends GuickCoreMojo {
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            guickEngine.runEngine("migrateToDb");
        } catch (RuntimeException ex) {
            getLog().error(ex);
            throw ex;
        }
    }
}
