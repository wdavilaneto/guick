/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.rest;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.commons.rest.dto.Pagination;
import br.mp.mprj.commons.rest.dto.SearchFilter;
import br.mp.mprj.sev.domain.CandidatoPadrao;
import br.mp.mprj.sev.api.service.ICandidatoPadraoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 *  CRUD Rest Json 'Controller' for entityCandidatoPadrao
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@RestController
@RequestMapping(value="/candidatoPadrao")
public class CandidatoPadraoController {

    private static PageRequest DEFAULT_PAGE = new PageRequest(0,20);
    private static final Logger LOGGER = LoggerFactory.getLogger(CandidatoPadraoController.class);

    @Resource(name = "candidatoPadraoService")
    private ICandidatoPadraoService candidatoPadraoService;

    /**
     * Returns an full, but Paged, list of all entities (CandidatoPadrao)
     * @param pagination
     * @return
     */
    @RequestMapping(method = GET)
    public HttpEntity<Page<CandidatoPadrao>> findAll(Pagination pagination) {
        return new ResponseEntity(candidatoPadraoService.findAll(pagination.getPageable()), OK);
    }

    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/search", method = POST)
    public HttpEntity<Page<CandidatoPadrao>> search(@RequestBody SearchFilter<CandidatoPadrao> filter) {
        return new ResponseEntity(candidatoPadraoService.search(filter.getContent() , filter.getPageable()), OK);
    }

    /**
     * Request first page of a text based search on all fields ignoring associations
     * @param text
     * @return
     */
    @RequestMapping(value = "/searchText", method = GET)
    public HttpEntity<Page<CandidatoPadrao>> searchTextGet(String text) {
        return new ResponseEntity(candidatoPadraoService.searchText(text , DEFAULT_PAGE ), OK);
    }
    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/searchText", method = RequestMethod.POST)
    public HttpEntity<Page<CandidatoPadrao>> searchText(@RequestBody SearchFilter<String> filter) {
        return new ResponseEntity(candidatoPadraoService.searchText(filter.getContent() , filter.getPageable()), OK);
    }
    /**
     * Return an entity,CandidatoPadrao ,with an Given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = GET)
    public HttpEntity<CandidatoPadrao> get(@PathVariable Long id) {
        return new ResponseEntity(candidatoPadraoService.findOne(id), OK);
    }

    /**
     * Deletes an entity with an given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = DELETE)
    public HttpEntity delete(@PathVariable Long id) {
        candidatoPadraoService.delete(id);
        return new HttpEntity(null);
    }

    /**
     * Simple save or update an entity
     * @param candidatoPadrao
     * @return
     */
    @RequestMapping(method = POST)
    public HttpEntity<CandidatoPadrao> save(@RequestBody CandidatoPadrao candidatoPadrao) {
        candidatoPadraoService.save(candidatoPadrao);
        return new ResponseEntity(candidatoPadrao, OK);
    }

}
