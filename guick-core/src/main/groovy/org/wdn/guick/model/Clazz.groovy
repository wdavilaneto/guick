package org.wdn.guick.model

import groovy.transform.CompileStatic

/**
 *
 */
@CompileStatic
class Clazz {

    def name

    @Override
    public String toString(){
        return name
    }

}
