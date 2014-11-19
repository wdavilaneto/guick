package org.wdn.guick.core
import groovy.transform.PackageScope
import org.springframework.stereotype.Component

import javax.annotation.Resource
/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 10:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@PackageScope
class GuickDelegate {

    @Resource VelocityWriter writer

    private List objectList
    private List templates

    def methodMissing(String name, Object args) {
        if (args.length == 1) {
            if (args[0] instanceof Closure) {
                args[0]()
                writer.process(objectList,templates )
            } else {
                throw new MissingMethodException(name, this.class, args as Object[])
            }
        } else {
            throw new MissingMethodException(name, this.class, args as Object[])
        }
    }

    def objects(List<Object> entries) {
        objectList = entries
    }

    def object(Object entry) {
        objectList = new ArrayList()
        objectList.add(entry)
    }

    def templates(def entries) {
        templates = entries
    }

    def propertyMissing(String name, Object args) {
        if (name == "templates") {
            templates = args
        } else {
            throw new MissingMethodException(name, this.class, args as Object[])
        }
    }



}
