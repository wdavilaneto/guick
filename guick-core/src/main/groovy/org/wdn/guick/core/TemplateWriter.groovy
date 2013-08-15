package org.wdn.guick.core

import freemarker.template.*
import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import org.springframework.stereotype.Component
import org.wdn.guick.model.Project

import javax.annotation.Resource

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 11:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@CompileStatic
@PackageScope
class TemplateWriter {

    @Resource Project project

    Configuration configuration

    public TemplateWriter() {
        configuration = new Configuration();
        //cfg.setDirectoryForTemplateLoading(new File("/where/you/store/templates"));
        configuration.setClassForTemplateLoading(Thread.currentThread().getClass(), "/")

        configuration.setObjectWrapper(new DefaultObjectWrapper());
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        configuration.setIncompatibleImprovements(new Version(2, 3, 20));
    }

    private void execute(Map templateParams, def obj = null) {
        HashMap<String, Object> context = new HashMap();
        context.put("project", project)
        if (obj != null) {
            context.put(getClassName(obj.class).toLowerCase(), obj)
        }
        if (templateParams?.context != null) {
            context.putAll((Map) templateParams.context)
        }
        processTemplate( (String)templateParams.input, (String)templateParams.output, context)
    }

    private void processTemplate(String input, String output, Map context) {
        Template template = configuration.getTemplate(input);
        File outputFile = getFileCratingAllNecessaryDirs(project.path.toString() + "/" + output)
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile)
        Writer out = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        template.process(context, out)
    }

    private static String getClassName(Class c) {
        final String className = c.getName()
        final int firstChar = className.lastIndexOf('.') + 1;
        if (firstChar > 0) {
            return className.substring(firstChar);
        }
        return className;
    }

    private File getFileCratingAllNecessaryDirs(final String output) {
        File file = new File(output)
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs()
        }
        return file
    }


}
