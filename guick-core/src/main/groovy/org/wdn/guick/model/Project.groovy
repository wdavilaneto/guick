package org.wdn.guick.model

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

    String group
    String name
    String path

    def metadata = [:]
    def pom;
    def datasource = [:]

    boolean runningFromPlugin = false

    def initialize(String rootPath = "") {
        if ("".equals(rootPath)) {
            runningFromPlugin = true
            println "****Initializing Plugin"
        }

        path = new File(rootPath).getCanonicalPath()

        def pomfilePath = path + "/pom.xml"
        if (new File(pomfilePath).exists()) {
            pom = new XmlSlurper(false, false).parse(pomfilePath);
            group = pom.groupId
            group = pom.artifactId

            ClassPathManager.addURLToSystemClassLoader(new URL("file:///${path}/target/classes/"))
        }

        def dsPath = path + "/src/main/resources/datasource.xml"
        if (new File(dsPath).exists()) {
            def xmlDS = new XmlSlurper(false, false).parse(dsPath);
            datasource.dialect = xmlDS.depthFirst().grep { it.@name == 'databasePlatform' }.'@value'*.text()[0]
            datasource.username = xmlDS.depthFirst().grep { it.@name == 'username' }.'@value'*.text()[0]
            datasource.password = xmlDS.depthFirst().grep { it.@name == 'password' }.'@value'*.text()[0]
            datasource.url = xmlDS.depthFirst().grep { it.@name == 'url' }.'@value'*.text()[0]
            datasource.driverClass = xmlDS.depthFirst().grep { it.@name == 'driverClass' }.'@value'*.text()[0]
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
