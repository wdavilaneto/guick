package org.wdn.guick.model

import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 11:45 PM
 * To change this template use File | Settings | File Templates.
 */
@CompileStatic
@Component
class GuickContext extends HashMap<String,Object> {

    Project project;

    public GuickContext(){
        super()
        this.put("project", project)
    }


}
