package org.wdn.guick.model

import groovy.transform.CompileStatic
import org.springframework.stereotype.Component
import sun.misc.ClassLoaderUtil

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
class Project {

    String group
    String name
    String path

    def metadata = [:]
    def pom;

    def initialize(String rootPath = "") {
        path = new File(rootPath).getCanonicalPath()

        if (new File(path+"/pom.xml").exists()) {
            pom = new XmlSlurper(false,false).parse(path+"/pom.xml");
            group = pom.groupId
            group = pom.artifactId
            //this.class.classLoader.rootLoader.addURL( new URL("file:///${path}/target/classes"))
            def loader = Project.class.getClassLoader()//new GroovyClassLoader()
            loader.addURL(new URL("file:///${path}/target/classes"))
        }

    }

    public String getName(){
        if (name != null) {
            return name
        }
        return path.split("/").last()
    }

    public String getGroup(){
        if (this.group != null) {
            return this.group
        }
        return getName()
    }

}
