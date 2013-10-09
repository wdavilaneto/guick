#set( $open  = "${")
#set( $close = "}")
#set( $entityBeanName = ${util.uncapitalize($entity.name)} )
package ${project.group}.${project.name}.presentation;

import ${project.group}.${project.name}.domain.${entity.name};
import ${project.group}.${project.name}.persistence.${entity.name}Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    private ${entity.name}Repository ${entityBeanName}Repository;

    /**
     * Method that lists/filter entity
     * @param ${util.uncapitalize($entity.name)}
     * @param model
     * @param pageable
     * @return viewId
     */
    @RequestMapping(value = "filter")
    public String list(@ModelAttribute(value = "${util.uncapitalize($entity.name)}") ${entity.name} ${util.uncapitalize($entity.name)}, Model model, Pageable pageable) {
        model.addAttribute("${util.uncapitalize($entity.name)}List", ${util.uncapitalize($entity.name)}Repository.find(${util.uncapitalize($entity.name)}, pageable));
        return "${entity.name}/filter";
    }

    /**
     * Controller method to edit of a existent entity
     * @param ${entityBeanName}
     * @param model
     * @return viewId
     */
    @RequestMapping(value ="edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") ${entity.name} ${entityBeanName}, Model model){
        model.addAttribute("${entityBeanName}", ${entityBeanName});
        return "${entity.name}/edit";
    }

    /**
     * Controller method to view of a existent entity
     * @param ${entityBeanName}
     * @param model
     * @return viewId
     */
    @RequestMapping(value ="show", method = RequestMethod.GET)
    public String show(@RequestParam("id") ${entity.name} ${util.uncapitalize($entity.name)}, Model model){
        model.addAttribute("${util.uncapitalize($entity.name)}", ${util.uncapitalize($entity.name)});
        return "${entity.name}/show";
    }

    /**
     * Controller method to delete an existent entity with a given id
     * @param ${entityBeanName}
     * @param model
     * @return viewId
     */
    @RequestMapping(value ="delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") ${entity.name} ${util.uncapitalize($entity.name)}, Model model){
        ${util.uncapitalize($entity.name)}Repository.delete(${util.uncapitalize($entity.name)}.id);
        return "redirect:/${entity.name}/filter.do";
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
        return "${entity.name}/create";
    }

    /**
     * Controller method to save entity
     * @param ${entityBeanName}
     * @param result
     * @param redirectAttributes
     * @param model
     * @return
     */
    @RequestMapping(value = "create" , method = RequestMethod.POST)
    public String save(@ModelAttribute(value = "${util.uncapitalize($entity.name)}") ${entity.name} ${util.uncapitalize($entity.name)}, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            LOGGER.debug("Errors found during form validation, reloading form.");
            return "${entity.name}/create";
        }
        ${entityBeanName}Repository.save(${entityBeanName});
        return "redirect:/${entity.name}/filter.do";
    }

}
