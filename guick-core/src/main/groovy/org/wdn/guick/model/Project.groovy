package org.wdn.guick.model

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.wdn.guick.util.ClassPathManager

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
class Project {

    private final Logger logger = LoggerFactory.getLogger(this.class)

    String group
    String name
    String path

    def pom
    def gradleProject
    def metadata = [:]

    def initialize(String rootPath) {
        if (rootPath == null){
            path = new File("").getCanonicalPath()
        } else {
            // is it Test... we need to add classpaths ...
            path = new File(rootPath).getCanonicalPath()
            ClassPathManager.addURLToSystemClassLoader(new URL("file",null,"${this.path}/build/classes/main/"))
            ClassPathManager.addURLToSystemClassLoader(new URL("file",null,"${this.path}/build/resources/main/"))
        }
        def pomfilePath = path + "/pom.xml"
        if (new File(pomfilePath).exists()) {
            pom = new XmlSlurper(false, false).parse(pomfilePath);
            group = pom.groupId
            name = pom.artifactId
        } else {
            // gradle classpath ?
        }
    }

    public String getName() {
        if (name != null) {
            return name
        }
        return path.split("/").last()
    }
    public String getGroup() {
        if (this.group != null) {
            return this.group
        }
        return getName()
    }

}
