/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.Parametro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityParametro
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
public interface IParametroService {

    /**
     * Returns an full, but Paged, list of all entities (Parametro)
     * @param pageable
     * @return
     */
    @Cacheable(value = "parametroList")
    public Page<Parametro> findAll(Pageable pageable);

    /**
    *
    * @param parametro
    * @param pageable
    * @return
    */
    @Cacheable(value = "parametroList")
    public Page<Parametro> search(Parametro parametro , Pageable pageable);

    /**
    *
    * @param text
    * @param pageable
    * @return
    */
    @Cacheable(value = "parametroList")
    public Page<Parametro> searchText(String text , Pageable pageable);

    /**
     * Return an entity,Parametro ,with an Given ID
     * @param id
     * @return
     */
    public Parametro findOne(Long id);

    /**
     * Deletes an entity with an given ID
     * @param id
     * @return
     */
    @CacheEvict(value="parametroList", allEntries=true)
    public void delete(Long id);

    /**
     * Simple save or update an entity
     * @param parametro
     * @return
     */
    @CacheEvict(value="parametroList", allEntries=true)
    public Parametro save(Parametro parametro);

}
