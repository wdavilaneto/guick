package org.wdn.guick.loader

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.wdn.guick.core.ResourceReader

import javax.annotation.Resource

@Component
class Ctags {

    private final Logger logger = LoggerFactory.getLogger(this.class)

    @Resource
    private ResourceReader resourceReader

    public Map load(String path) {
        def process = """ctags --recurse --exclude=boost $path """.execute()
        process.waitFor()

        def tags
        try {
            tags = resourceReader.getReader("$path/tags");
        } catch (FileNotFoundException e) {
            tags = new FileReader(new File("tags"));
        }

        tags.eachLine { line ->
            println "${line}"
        }

        return null
    }


}
