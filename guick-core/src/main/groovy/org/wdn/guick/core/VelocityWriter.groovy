package org.wdn.guick.core

import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity
import org.apache.velocity.runtime.RuntimeConstants
import org.apache.velocity.slf4j.Slf4jLogChute
import org.springframework.stereotype.Component

@Component
@CompileStatic
@PackageScope
class VelocityWriter  extends AbstractTemplateWriter{


    public VelocityWriter() {
        Velocity.setProperty("resource.loader", "classpath");
        Velocity.setProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.setProperty("output.encoding", "UTF-8");
        Velocity.setProperty("input.encoding", "UTF-8");
        Velocity.setProperty("default.contentType", "UTF-8");

        Velocity.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM, new Slf4jLogChute())
        //Inicializa o Velociy.
        Velocity.init()
    }

    @Override
    protected doWriteTemplate(String input, Map context, String output) {
        File outputFile = getFileCratingAllNecessaryDirs(project.path.toString() + "/" + output)
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile)
        Writer out = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        Velocity.mergeTemplate(input, "UTF-8" , new VelocityContext(context), out )
        out.close()
    }
}