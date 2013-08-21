package org.wdn.guick.core

import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import org.springframework.stereotype.Component

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

    public Reader getRunner(String runner) {

        return getResource("${runner}.gdsl")
    }

    public Reader getResource(String runner) {
        InputStream stream = ResourceReader.class.getClassLoader().getResourceAsStream(runner)
        if (stream) {
            return new InputStreamReader(stream);
        }
        throw new FileNotFoundException("File ${runner} not found on classpath")
    }

    public InputStream getResourceAsStream(String resource) {
        ResourceReader.class.getClassLoader().getResourceAsStream(resource)
    }


}
