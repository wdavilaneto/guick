package ${project.group}.${project.name}.presentation;

import com.wdavilaneto.wdavilaneto.AbstractTest;
import com.wdavilaneto.wdavilaneto.domain.${entity.name};
import com.wdavilaneto.wdavilaneto.persistence.${entity.name}Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.junit.Before
import org.junit.Test
import javax.annotation.Resource;

/**
 *  CRUD Controller Test for entity${entity.name}
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
class ${entity.name}ControllerTest extends AbstractTest{

    private static final Logger LOGGER = LoggerFactory.getLogger(${entity.name}ControllerTest.class);

    private PersonController personController;

    @Before
    void setUp() {
        super.setUp()
    }

    @Test
    void "test filter"() {
        // Calling template method to create Form Screen
        assert true
    }

}
