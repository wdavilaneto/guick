package org.wdn.guick.dsl

import org.springframework.stereotype.Component
import org.wdn.guick.model.Project
import org.wdn.guick.view.TemplateWriter

import javax.annotation.Resource

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 10:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
class GuickDelegate {

    @Resource Project project
    @Resource TemplateWriter writer

    private objects
    private templates

    def methodMissing(String name, Object args) {
        if (args.length == 1) {
            if (args[0] instanceof Closure) {
                args[0]()

                if (objects == null) {
                    for (def templateParams : templates) {
                        writer.execute templateParams
                    }
                } else {
                    for (def obj : objects) {
                        for (def templateParams : templates) {
                            writer.execute templateParams, obj
                        }
                    }
                }

            } else {
                throw new MissingMethodException(name, this.class, args as Object[])
            }
        } else {
            throw new MissingMethodException(name, this.class, args as Object[])
        }
    }

    def objects(List<Object> entries) {
        objects = entries
    }

    def object(Object entry) {
        objects = new ArrayList()
        objects.add(entry)
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
