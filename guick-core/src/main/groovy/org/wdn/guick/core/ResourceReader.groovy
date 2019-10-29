package org.wdn.guick.core

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.UrlResource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.stereotype.Component
import org.wdn.guick.model.Project

import javax.annotation.Resource

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 10:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
class ResourceReader {

    private final Logger logger = LoggerFactory.getLogger(this.class)

    @Resource
    Project project

    /**
     * Get file reader
     * @param runner
     * @return
     */
    Reader getReader(String runner) {
        InputStream stream = getResourceAsStream(runner)
        if (stream) {
            return new InputStreamReader(stream);
        } else {
            logger.info("${runner} not found on Guick. Searching on ${project.path}/src/main/guick")
        }
        if (project) {
            def strClientPath = "file://${project.path}/src/main/guick"
            stream = new FileInputStream("${strClientPath}/${runner}")
            if (stream) {
                return new InputStreamReader(stream);
            }
        }
        throw new FileNotFoundException("File ${runner} not found on classpath")
    }

    InputStream getResourceAsStream(String resource) {
        ResourceReader.class.getClassLoader().getResourceAsStream(resource)
    }


}
