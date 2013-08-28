package org.wdn.guick

import java.lang.reflect.Method

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/28/13
 * Time: 12:45 AM
 * To change this template use File | Settings | File Templates.
 */
class Guick {

    private static instance = null

    public Guick getInstance (){
        if (instance == null) {
            instance = new Guick();
        }
        return instance;
    }

}
