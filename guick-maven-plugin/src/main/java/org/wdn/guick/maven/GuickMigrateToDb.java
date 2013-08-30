/*
 * Esse arquivo pertence a Petrobras e nao pode ser utilizado fora
 * dessa empresa sem previa autorizacao.
 */

package org.wdn.guick.maven;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.classworlds.ClassWorld;
import org.codehaus.plexus.classworlds.realm.ClassRealm;
import org.codehaus.plexus.classworlds.realm.DuplicateRealmException;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

/**
 * The class GuickMigrateToDb
 *
 * @goal migrate-to-db
 * @phase process-sources
 */
public class GuickMigrateToDb extends GuickCoreMojo {

    /**
     * The maven project.
     *
     * @since 1.0
     */
    @Component
    protected MavenProject project;
    /**
     * @since 1.0
     */
    @Parameter(defaultValue = "${plugin.artifacts}", required = true)
    private List<Artifact> pluginArtifacts;

    /**
     * Class loader class to set.
     *
     * @since 2.0
     */
    @Parameter
    protected String classLoaderClass;

    /**
     * @since 1.0
     */
    private ClassRealm guickRealm;

    /**
     * @since 2.0
     */
//    @Component
//    private ClassLoaderEntriesCalculator classLoaderEntriesCalculator;
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            guickEngine.runEngine("migrateToDb");
        } catch (RuntimeException ex) {
            getLog().error(ex);
            throw ex;
        }
    }

    protected ClassRealm getGuickClassLoader() throws MojoExecutionException {
        if (this.guickRealm != null) {
            return guickRealm;
        }
        try {
            ClassWorld world = new ClassWorld();
            ClassRealm root = world.newRealm("guick", Thread.currentThread().getContextClassLoader());

            for (@SuppressWarnings("rawtypes") Iterator i = pluginArtifacts.iterator(); i.hasNext(); ) {
                Artifact pluginArtifact = (Artifact) i.next();
                // add all plugin artifacts see https://issues.apache.org/jira/browse/MTOMCAT-122
                if (pluginArtifact.getFile() != null) {
                    root.addURL(pluginArtifact.getFile().toURI().toURL());
                }

            }
            guickRealm = root;
            return root;
        } catch (DuplicateRealmException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        } catch (MalformedURLException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

}
