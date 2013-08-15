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
        // Delega a layerDelegate as realizacoes dentro da closure da referida layer
        cl.delegate = guickDelegate
        // Set Estrategia que define que as tentativas de resolver as propriedades
        // serao feitas primeiro pela "delegate" (cl.delegate) e nao o owner (this)
        cl.resolveStrategy = Closure.DELEGATE_FIRST
        // Executa o codigo da Clusure em questao
        cl()
    }
}
