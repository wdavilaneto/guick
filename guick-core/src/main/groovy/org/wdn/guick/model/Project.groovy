package org.wdn.guick.model

import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@CompileStatic
class Project {

    String group
    String name
    String path

    def initialize(String rootPath) {
        path = new File(rootPath).getCanonicalPath()
    }

    public String getName(){
        if (name != null) {
            return name
        }
        return path.split("/").last()
    }

    public String getGroup(){
        if (group != null) {
            return group
        }
        return getName()
    }

}
