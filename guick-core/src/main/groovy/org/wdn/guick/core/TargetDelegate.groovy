package org.wdn.guick.core

import groovy.transform.PackageScope
import org.springframework.stereotype.Component

import javax.annotation.Resource

@Component
@PackageScope
class TargetDelegate {

    @Resource GuickDelegate guickDelegate

    def propertyMissing(String name) {
        println  "unprocessed property " + name
    }

    File file (String name) {
        return new File(name)
    }

    def methodMissing(String name, Object args) {
        if (args.length == 1) {
            // logo apos o "qualquer-coisa" existe um Closure { } ?
            if (args[0] instanceof Closure) {
                setLayerDelegateAndExecute(args[0])
            } else {
                println "unprocessed method: " + name
            }
        } else {
            println "unprocessed method: " + name
        }
    }

    private setLayerDelegateAndExecute(Closure cl) {
        cl.delegate = guickDelegate
        cl.resolveStrategy = Closure.DELEGATE_FIRST
        cl()
    }
}
