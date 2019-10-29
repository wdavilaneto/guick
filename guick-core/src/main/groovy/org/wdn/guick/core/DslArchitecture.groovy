package org.wdn.guick.core

import antlr.CppCodeGenerator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.wdn.guick.loader.Database
import org.wdn.guick.loader.Jpa
import org.wdn.guick.loader.Json
import org.wdn.guick.model.Project
import org.wdn.guick.util.StringUtil

import javax.annotation.Resource
/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 10:43 PM
 */
@Component
class DslArchitecture {

    private final Logger logger = LoggerFactory.getLogger(this.class)

    @Resource TargetDelegate targetDelegate
    @Resource Project project
    @Resource ResourceReader reader
    @Resource Json json
    @Resource Jpa jpa
    @Resource Database database

    void runEngine(String target) {

        Binding binding = new Binding()
        binding.setVariable("project", project)
        binding.setVariable("util",new StringUtil())
        binding.setVariable("json", json)
        binding.setVariable("jpa", jpa)
        binding.setVariable("database", database)

        Script dslScript = new GroovyShell(binding).parse(getRunner(target))

        dslScript.metaClass = createEMC(dslScript.class, {
            ExpandoMetaClass emc ->
                emc.guick = {
                    Closure cl ->
                        cl.delegate = targetDelegate
                        cl.resolveStrategy = Closure.DELEGATE_FIRST
                        cl()
                }
        })
        try {
            dslScript.run()
        } catch (RuntimeException e) {
            println "Erro ao executar o script ${target}"
            throw e;
        }
    }

    /**
     * get gdsl file
     * @param runner
     * @return
     */
    private Reader getRunner(String runner) {
        try {
            return reader.getReader("${runner}.gdsl")
        } catch (FileNotFoundException ex) {
            logger.error(ex.getMessage() + "\nRunner File: ${runner}.gdsl wore not found \nmake sure it's on classpath ")
            throw ex;
        }
    }

    private ExpandoMetaClass createEMC(Class clazz, Closure cl) {
        ExpandoMetaClass emc = new ExpandoMetaClass(clazz, false)
        cl(emc)
        emc.initialize()
        return emc
    }


}
