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
class GdslReader {

    public Reader get(String runner) {
        InputStream stream =  GdslReader.class.getClassLoader().getResourceAsStream("${runner}.gdsl")
        if (stream) {
            return new InputStreamReader( stream );
        }
        throw new FileNotFoundException("${runner}.groovy not found on classpath")
    }

}
