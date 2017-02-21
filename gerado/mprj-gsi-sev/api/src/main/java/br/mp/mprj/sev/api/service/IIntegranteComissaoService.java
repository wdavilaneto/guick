/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.IntegranteComissao;
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
 *  CRUD Rest Json 'Controller' for entityIntegranteComissao
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
public interface IIntegranteComissaoService {

    /**
     * Returns an full, but Paged, list of all entities (IntegranteComissao)
     * @param pageable
     * @return
     */
    public Page<IntegranteComissao> findAll(Pageable pageable);

    /**
    *
    * @param integranteComissao
    * @param pageable
    * @return
    */
    public Page<IntegranteComissao> search(IntegranteComissao integranteComissao , Pageable pageable);

    /**
    *
    * @param text
    * @param pageable
    * @return
    */
    public Page<IntegranteComissao> searchText(String text , Pageable pageable);

    /**
     * Return an entity,IntegranteComissao ,with an Given ID
     * @param id
     * @return
     */
    public IntegranteComissao findOne(Long id);

    /**
     * Deletes an entity with an given ID
     * @param id
     * @return
     */
    public void delete(Long id);

    /**
     * Simple save or update an entity
     * @param integranteComissao
     * @return
     */
    public IntegranteComissao save(IntegranteComissao integranteComissao);

}
