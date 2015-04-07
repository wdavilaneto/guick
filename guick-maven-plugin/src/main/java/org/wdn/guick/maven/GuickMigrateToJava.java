/*
 * Esse arquivo pertence a e nao pode ser utilizado fora
 * dessa empresa sem previa autorizacao.
 */

package org.wdn.guick.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * The class GuickMigrateToJava
 * @goal migrate-to-java
 * @phase process-sources
 */
public class GuickMigrateToJava extends GuickCoreMojo{
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            guickEngine.runEngine("migrateToJava");
        } catch (RuntimeException ex) {
            getLog().error(ex);
            throw ex;
        }
    }
}
