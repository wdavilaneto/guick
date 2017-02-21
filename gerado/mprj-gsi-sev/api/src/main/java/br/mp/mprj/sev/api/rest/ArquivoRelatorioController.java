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
import br.mp.mprj.sev.domain.ArquivoRelatorio;
import br.mp.mprj.sev.api.service.IArquivoRelatorioService;
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
 *  CRUD Rest Json 'Controller' for entityArquivoRelatorio
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@RestController
@RequestMapping(value="/arquivoRelatorio")
public class ArquivoRelatorioController {

    private static PageRequest DEFAULT_PAGE = new PageRequest(0,20);
    private static final Logger LOGGER = LoggerFactory.getLogger(ArquivoRelatorioController.class);

    @Resource(name = "arquivoRelatorioService")
    private IArquivoRelatorioService arquivoRelatorioService;

    /**
     * Returns an full, but Paged, list of all entities (ArquivoRelatorio)
     * @param pagination
     * @return
     */
    @RequestMapping(method = GET)
    public HttpEntity<Page<ArquivoRelatorio>> findAll(Pagination pagination) {
        return new ResponseEntity(arquivoRelatorioService.findAll(pagination.getPageable()), OK);
    }

    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/search", method = POST)
    public HttpEntity<Page<ArquivoRelatorio>> search(@RequestBody SearchFilter<ArquivoRelatorio> filter) {
        return new ResponseEntity(arquivoRelatorioService.search(filter.getContent() , filter.getPageable()), OK);
    }

    /**
     * Request first page of a text based search on all fields ignoring associations
     * @param text
     * @return
     */
    @RequestMapping(value = "/searchText", method = GET)
    public HttpEntity<Page<ArquivoRelatorio>> searchTextGet(String text) {
        return new ResponseEntity(arquivoRelatorioService.searchText(text , DEFAULT_PAGE ), OK);
    }
    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/searchText", method = RequestMethod.POST)
    public HttpEntity<Page<ArquivoRelatorio>> searchText(@RequestBody SearchFilter<String> filter) {
        return new ResponseEntity(arquivoRelatorioService.searchText(filter.getContent() , filter.getPageable()), OK);
    }
    /**
     * Return an entity,ArquivoRelatorio ,with an Given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = GET)
    public HttpEntity<ArquivoRelatorio> get(@PathVariable Long id) {
        return new ResponseEntity(arquivoRelatorioService.findOne(id), OK);
    }

    /**
     * Deletes an entity with an given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = DELETE)
    public HttpEntity delete(@PathVariable Long id) {
        arquivoRelatorioService.delete(id);
        return new HttpEntity(null);
    }

    /**
     * Simple save or update an entity
     * @param arquivoRelatorio
     * @return
     */
    @RequestMapping(method = POST)
    public HttpEntity<ArquivoRelatorio> save(@RequestBody ArquivoRelatorio arquivoRelatorio) {
        arquivoRelatorioService.save(arquivoRelatorio);
        return new ResponseEntity(arquivoRelatorio, OK);
    }

}
