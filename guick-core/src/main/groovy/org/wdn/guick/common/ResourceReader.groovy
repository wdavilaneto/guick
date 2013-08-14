package org.wdn.guick.common

import groovy.transform.CompileStatic
import org.codehaus.groovy.reflection.ReflectionUtils
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
class ResourceReader {

    public Reader get(String runner) {
        //InputStream stream = ReflectionUtils.getCallingClass(0).getResourceAsStream("${runner}.groovy")
        //InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("${runner}.groovy")
        InputStream stream =  ResourceReader.class.getClassLoader().getResourceAsStream("/${runner}.groovy")
        if (stream) {
            return new InputStreamReader( stream );
        }
        throw new FileNotFoundException("${runner}.groovy not found on classpath")
    }

}
