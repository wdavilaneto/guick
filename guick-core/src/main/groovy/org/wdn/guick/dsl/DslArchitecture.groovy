package org.wdn.guick.dsl

import org.springframework.stereotype.Component
import org.wdn.guick.common.ResourceReader
import org.wdn.guick.model.GuickContext

import javax.annotation.Resource

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 10:43 PM
 */
@Component
class DslArchitecture {

    @Resource GuickDelegate guickDelegate
    @Resource GuickContext context
    @Resource ResourceReader reader

    void runEngine(String target) {

        Binding binding = new Binding();
        binding.setVariable("project", context.project)

        Script dslScript = new GroovyShell(binding).parse(reader.get(target))

        dslScript.metaClass = createEMC(dslScript.class, {
            ExpandoMetaClass emc ->
                emc.guick = {
                    Closure cl ->
                        cl.delegate = guickDelegate
                        cl.resolveStrategy = Closure.DELEGATE_FIRST
                        cl()
                }
        })
        dslScript.run()

    }

    private ExpandoMetaClass createEMC(Class clazz, Closure cl) {
        ExpandoMetaClass emc = new ExpandoMetaClass(clazz, false)
        cl(emc)
        emc.initialize()
        return emc
    }


}
