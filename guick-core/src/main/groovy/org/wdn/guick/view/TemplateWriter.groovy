package org.wdn.guick.view

import freemarker.template.Configuration
import freemarker.template.DefaultObjectWrapper
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import freemarker.template.Version
import org.springframework.stereotype.Component

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 11:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
class TemplateWriter {

    Configuration configuration

    public TemplateWriter() {
        Configuration cfg = new Configuration();
        //cfg.setDirectoryForTemplateLoading(new File("/where/you/store/templates"));
        cfg.setClassForTemplateLoading(Thread.currentThread().getClass(), "/" )

        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        // At least in new projects, specify that you want the fixes that aren't
        // 100% backward compatible too (these are very low-risk changes as far as the
        // 1st and 2nd version number remains):
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
    }

    public void execute(String input, String output, Map context) {
        Template template = configuration.getTemplate(input);
        File outputFile = getFileCratingAllNecessaryDirs(output)
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile)
        Writer out = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        template.process(context , out)
    }

    private File getFileCratingAllNecessaryDirs(final String output) {
        File file = new File(output)
        if (!file.exists()) {
            file.getParentFile().mkdirs()
        }
        return file
    }
}
