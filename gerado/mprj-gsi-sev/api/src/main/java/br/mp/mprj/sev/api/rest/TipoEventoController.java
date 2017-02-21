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
import br.mp.mprj.sev.domain.TipoEvento;
import br.mp.mprj.sev.api.service.ITipoEventoService;
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
 *  CRUD Rest Json 'Controller' for entityTipoEvento
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@RestController
@RequestMapping(value="/tipoEvento")
public class TipoEventoController {

    private static PageRequest DEFAULT_PAGE = new PageRequest(0,20);
    private static final Logger LOGGER = LoggerFactory.getLogger(TipoEventoController.class);

    @Resource(name = "tipoEventoService")
    private ITipoEventoService tipoEventoService;

    /**
     * Returns an full, but Paged, list of all entities (TipoEvento)
     * @param pagination
     * @return
     */
    @RequestMapping(method = GET)
    public HttpEntity<Page<TipoEvento>> findAll(Pagination pagination) {
        return new ResponseEntity(tipoEventoService.findAll(pagination.getPageable()), OK);
    }

    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/search", method = POST)
    public HttpEntity<Page<TipoEvento>> search(@RequestBody SearchFilter<TipoEvento> filter) {
        return new ResponseEntity(tipoEventoService.search(filter.getContent() , filter.getPageable()), OK);
    }

    /**
     * Request first page of a text based search on all fields ignoring associations
     * @param text
     * @return
     */
    @RequestMapping(value = "/searchText", method = GET)
    public HttpEntity<Page<TipoEvento>> searchTextGet(String text) {
        return new ResponseEntity(tipoEventoService.searchText(text , DEFAULT_PAGE ), OK);
    }
    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/searchText", method = RequestMethod.POST)
    public HttpEntity<Page<TipoEvento>> searchText(@RequestBody SearchFilter<String> filter) {
        return new ResponseEntity(tipoEventoService.searchText(filter.getContent() , filter.getPageable()), OK);
    }
    /**
     * Return an entity,TipoEvento ,with an Given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = GET)
    public HttpEntity<TipoEvento> get(@PathVariable Long id) {
        return new ResponseEntity(tipoEventoService.findOne(id), OK);
    }

    /**
     * Deletes an entity with an given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = DELETE)
    public HttpEntity delete(@PathVariable Long id) {
        tipoEventoService.delete(id);
        return new HttpEntity(null);
    }

    /**
     * Simple save or update an entity
     * @param tipoEvento
     * @return
     */
    @RequestMapping(method = POST)
    public HttpEntity<TipoEvento> save(@RequestBody TipoEvento tipoEvento) {
        tipoEventoService.save(tipoEvento);
        return new ResponseEntity(tipoEvento, OK);
    }

}
