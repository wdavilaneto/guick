package org.wdn.guick.core

import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import org.springframework.stereotype.Component
import org.wdn.guick.model.Project
import org.wdn.guick.util.ClassPathManager

import javax.annotation.Resource

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 10:53 PM
 * To change this template use File | Settings | File Templates.
 */
@CompileStatic
@Component
@PackageScope
class ResourceReader {

    @Resource
    Project project
    private static boolean initizlied = false


    public Reader getRunner(String runner) {
        return getResource("${runner}.gdsl")
    }

    public Reader getResource(String runner) {
        def strClientPath = "file://${project.path}/src/main/guick"
        if (initizlied) {
            try {
                ClassPathManager.addURLToSystemClassLoader(new URL("file:///${project.path}/src/main/guick/"));
            } catch (Exception e) {
                // ignore ...
            }
        }
        InputStream stream = ResourceReader.class.getClassLoader().getResourceAsStream(runner)
        if (stream) {
            return new InputStreamReader(stream);
        }
        stream = new FileInputStream("${strClientPath}/${runner}")
        if (stream) {
            return new InputStreamReader(stream);
        }
        throw new FileNotFoundException("File ${runner} not found on classpath")
    }

    public InputStream getResourceAsStream(String resource) {
        ResourceReader.class.getClassLoader().getResourceAsStream(resource)
    }


}
