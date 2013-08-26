package org.wdn.guick.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @goal guick
 * @phase process-sources
 */
public class Guick extends GuickCoreMojo {


    /**
     * @parameter expression="${command}"
     */
    private String command;

    public void execute() throws MojoExecutionException, MojoFailureException {

        try {
            guickEngine.runEngine(command);
        } catch (RuntimeException ex) {
            getLog().error(ex);
            throw ex;
        }
    }

}
