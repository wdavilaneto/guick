package org.wdn.guick.core

import freemarker.template.Configuration
import freemarker.template.DefaultObjectWrapper
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import freemarker.template.Version
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.wdn.guick.model.Project

import javax.annotation.Resource
import java.nio.ByteBuffer
import java.nio.channels.Channels
import java.nio.channels.ReadableByteChannel
import java.nio.channels.WritableByteChannel

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/16/13
 * Time: 12:13 AM
 * To change this template use File | Settings | File Templates.
 */
abstract class AbstractTemplateWriter {

    @Resource ResourceReader reader
    @Resource Project project
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    abstract protected doWriteTemplate(String input, def context, String output);

    public process(List objects, List<Map<String, Object>> templates) {
        if (objects == null) {
            for (def prams : templates) {
                def resources = resolver.getResources(prams.input.toString())
                if (resources.size() == 1 && !resources[0].filename.contains(".")) {
                    def insideResources = resolver.getResources(prams.input.toString() + "/*")
                    executeDinamic(insideResources, prams.input.toString(), prams.output?.toString(), prams.context)
                } else {
                    String output = prams.output
                    String normalizedInput = prams.input.toString().replaceFirst(prams.input.toString().split("/")[0],"")
                    if (output == null) {
                        output = normalizedInput
                    } else {
                        if (output.endsWith("/")) {
                            output = output + normalizedInput
                        }
                    }
                    execute(prams.input.toString(), output, prams.context)
                }
            }
        } else {
            for (def obj : objects) {
                for (def prams : templates) {
                    def resources = resolver.getResources(prams.input.toString())
                    if (resources.size() == 1 && !resources[0].isReadable()) {
                        def insideResources = resolver.getResources(prams.input.toString() + "/*")
                        executeDinamic(insideResources, prams.input.toString(), prams.output?.toString(), prams.context, obj)
                    } else {
                        String output = prams.output
                        String normalizedInput = prams.input.toString().replaceFirst(prams.input.toString().split("/")[0],"")
                        if (output == null) {
                            output = normalizedInput
                        } else {
                            if (output.endsWith("/")) {
                                output = output + normalizedInput
                            }
                        }
                        execute(prams.input.toString(), output, prams.context, obj)
                    }
                }
            }
        }
    }

    private executeDinamic(org.springframework.core.io.Resource[] resources, String input, String output, def context, def obj = true) {
        for (def resource : resources) {
            if (resource.isReadable()) {
                String newInput = input.toString() + "/" + resource.getFilename()
                String newOutput = (output == null ? newInput : output + "/" + resource.getFilename())
                execute(newInput, newOutput, context, obj)
            } else {
                String pathToAdd =  "/${resource.getFilename()}/"
                def insideResources = resolver.getResources(input + "${pathToAdd}*")
                executeDinamic(insideResources, input + pathToAdd ,(output==null ? output : output+pathToAdd )  , context, obj)
            }

        }
    }

    private void execute(String input, String output, def extraContext = null, def obj = null) {
        HashMap<String, Object> context = new HashMap();
        context.put("project", project)
        if (obj != null) {
            context.put(getClassName(obj.class).toLowerCase(), obj)
        }
        if (extraContext != null) {
            if (extraContext instanceof Map) {
                context.putAll(extraContext)
            }
            //TODO ...
        }

        output = normalizeOutput(input , output)

        if (input.endsWith(".ftl")) {
            doWriteTemplate(input, context, output);
        } else {
            doCopyResrouce(input, output)
        }
    }

    String normalizeOutput(final String input,final  String output) {
        def outputFileName = output.split("/").last()
        if (outputFileName.contains(".")) {
            return (output.endsWith(".ftl")? output[0..-5] : output )
        }
        def inputFilename = input.split("/").last().replaceFirst(".ftl","")
        return output + "/" + inputFilename ;
    }

    private doCopyResrouce(String input, String output) {
        File outputFile = getFileCratingAllNecessaryDirs(project.path.toString() + "/" + output)
        println "writing ${outputFile.getAbsolutePath()}"
        final OutputStream outputStram = new FileOutputStream(outputFile);
        final InputStream inputStream = reader.getResourceAsStream(input);
        if (inputStream != null) {
            // get an channel from the stream
            final ReadableByteChannel inputChannel = Channels.newChannel(inputStream);
            final WritableByteChannel outputChannel = Channels.newChannel(outputStram);
            // copy the channels
            fastChannelCopy(inputChannel, outputChannel);
            // closing the channels
            inputChannel.close();
            outputChannel.close()
        } else {
            println "File  ${input} not found"
        }
    }

    public static void fastChannelCopy(final ReadableByteChannel src, final WritableByteChannel dest) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(buffer) != -1) {
            // prepare the buffer to be drained
            buffer.flip();
            // write to the channel, may block
            dest.write(buffer);
            // If partial transfer, shift remainder down
            // If buffer is empty, same as doing clear()
            buffer.compact();
        }
        // EOF will leave buffer in fill state
        buffer.flip();
        // make sure the buffer is fully drained.
        while (buffer.hasRemaining()) {
            dest.write(buffer);
        }
    }


    private static String getClassName(Class c) {
        final String className = c.getName()
        final int firstChar = className.lastIndexOf('.') + 1;
        if (firstChar > 0) {
            return className.substring(firstChar);
        }
        return className;
    }

    protected File getFileCratingAllNecessaryDirs(final String output) {
        File file = new File(output)
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs()
        }
        return file
    }
}
