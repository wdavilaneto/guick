package org.wdn.guick.loader

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.springframework.stereotype.Component
import org.wdn.guick.core.ResourceReader

import javax.annotation.Resource

@Component
class Json {

    @Resource
    private ResourceReader resourceReader

    private slurper = new JsonSlurper()

    public Object load(String resourceOrFile) {
        def reader
        try {
            reader = resourceReader.getReader(resourceOrFile);
        } catch (FileNotFoundException e) {
            reader = new FileReader(new File(resourceOrFile));
        }
        return slurper.parse(reader)
    }

    public String toJson(def object) {
        return new JsonBuilder(object).toPrettyString()
    }

    public void write(File target, def json) {
        target.withWriter {
            it.write(toJson(json));
        }
    }

    public Object loadText(String text) {
        return slurper.parseText(text)
    }

}
