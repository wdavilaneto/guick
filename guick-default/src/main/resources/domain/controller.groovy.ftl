package ${project.group}.${project.name}.presentation;

import com.wdavilaneto.wdavilaneto.domain.${entity.name};
import com.wdavilaneto.wdavilaneto.persistence.${entity.name}Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 *  CRUD Controller for entity${entity.name}
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Controller
@RequestMapping("${entity.name}")
class ${entity.name}Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(${entity.name}Controller.class);

    @Resource
    private PersonRepository personRepository;

    /**
     * Method that lists/filter entity
     * @param ${util.uncapitalize($entity.name)}
     * @param model
     * @param pageable
     * @return viewId
     */
    @RequestMapping(value = "filter")
    public String list(@ModelAttribute(value = "${util.uncapitalize($entity.name)}") ${entity.name} ${util.uncapitalize($entity.name)}, Model model, Pageable pageable) {
        // Calling template method to create Form Screen
        model.addAttribute("${util.uncapitalize($entity.name)}List", ${util.uncapitalize($entity.name)}Repository.findAll(pageable));
        return "${entity.name}/filter";
    }

    /**
     * Controller method to creation of a new entity
     * @param ${util.uncapitalize($entity.name)}
     * @param model
     * @param pageable
     * @return viewId
     */
    @RequestMapping(value = "create" , method = RequestMethod.GET)
    public String create(@ModelAttribute(value = "${util.uncapitalize($entity.name)}") ${entity.name} ${util.uncapitalize($entity.name)}, Model model) {
        // Calling template method to create Form Screen
        model.addAttribute("${util.uncapitalize($entity.name)}",  ${entity.name});
        return "${entity.name}/create";
    }

    @RequestMapping(value = "create" , method = RequestMethod.POST)
    public String save(@ModelAttribute(value = "${util.uncapitalize($entity.name)}") ${entity.name} ${util.uncapitalize($entity.name)}, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            LOGGER.debug("Errors found during form validation, reloading form.");
            return "${entity.name}/create";
        }
        personRepository.save(person)
        return "redirect:/${entity.name}/filter.do";
    }

}
