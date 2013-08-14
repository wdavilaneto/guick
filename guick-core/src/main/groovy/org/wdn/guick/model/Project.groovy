package org.wdn.guick.model

import org.springframework.stereotype.Component

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
class Project {

    def name
    def path

    def initialize(String rootPath) {
        path = new File(rootPath).getCanonicalPath()
    }

}
