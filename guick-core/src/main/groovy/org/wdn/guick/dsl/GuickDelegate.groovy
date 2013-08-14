package org.wdn.guick.dsl

import org.springframework.stereotype.Component
import org.wdn.guick.model.GuickContext
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

    @Resource TemplateWriter writer
    @Resource GuickContext context

    private objects
    private templates

    def methodMissing(String name, Object args) {
        if (args.length == 1) {
            if (args[0] instanceof Closure) {
                args[0]()

                if (objects == null) {
                    for (def templateParams : templates) {
                        processTemplate templateParams
                    }
                } else {
                    for (def obj : objects) {
                        for (def templateParams : templates) {
                            processTemplate templateParams
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

    private void processTemplate(def templateParams) {
        if (templateParams?.context != null) {
            context.putAll(templateParams.context)
        }
        writer.execute(templateParams.input, templateParams.output, context)
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
