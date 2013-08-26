package org.wdn.guick.core

import freemarker.template.*
import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import org.springframework.stereotype.Component

/**
 * Class responsable for write a given rule based input to intuitive relative output
 * with a agiven context
 */
@Component
@CompileStatic
@PackageScope
class TemplateWriter extends AbstractTemplateWriter {

    Configuration configuration

    public TemplateWriter() {
        configuration = new Configuration();
        configuration.setClassForTemplateLoading(Thread.currentThread().getClass(), "/")
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        configuration.setIncompatibleImprovements(new Version(2, 3, 20));
    }

    @Override
    protected doWriteTemplate(String input, Map context, String output) {
        Template template = configuration.getTemplate(input);
        File outputFile = getFileCratingAllNecessaryDirs(project.path.toString() + "/" + output)
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile)
        Writer out = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        template.process(context, out)
        out.close()
    }

}
