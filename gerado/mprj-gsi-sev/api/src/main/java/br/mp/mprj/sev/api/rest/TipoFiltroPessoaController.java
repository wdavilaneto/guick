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
import br.mp.mprj.sev.domain.TipoFiltroPessoa;
import br.mp.mprj.sev.api.service.ITipoFiltroPessoaService;
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
 *  CRUD Rest Json 'Controller' for entityTipoFiltroPessoa
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@RestController
@RequestMapping(value="/tipoFiltroPessoa")
public class TipoFiltroPessoaController {

    private static PageRequest DEFAULT_PAGE = new PageRequest(0,20);
    private static final Logger LOGGER = LoggerFactory.getLogger(TipoFiltroPessoaController.class);

    @Resource(name = "tipoFiltroPessoaService")
    private ITipoFiltroPessoaService tipoFiltroPessoaService;

    /**
     * Returns an full, but Paged, list of all entities (TipoFiltroPessoa)
     * @param pagination
     * @return
     */
    @RequestMapping(method = GET)
    public HttpEntity<Page<TipoFiltroPessoa>> findAll(Pagination pagination) {
        return new ResponseEntity(tipoFiltroPessoaService.findAll(pagination.getPageable()), OK);
    }

    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/search", method = POST)
    public HttpEntity<Page<TipoFiltroPessoa>> search(@RequestBody SearchFilter<TipoFiltroPessoa> filter) {
        return new ResponseEntity(tipoFiltroPessoaService.search(filter.getContent() , filter.getPageable()), OK);
    }

    /**
     * Request first page of a text based search on all fields ignoring associations
     * @param text
     * @return
     */
    @RequestMapping(value = "/searchText", method = GET)
    public HttpEntity<Page<TipoFiltroPessoa>> searchTextGet(String text) {
        return new ResponseEntity(tipoFiltroPessoaService.searchText(text , DEFAULT_PAGE ), OK);
    }
    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/searchText", method = RequestMethod.POST)
    public HttpEntity<Page<TipoFiltroPessoa>> searchText(@RequestBody SearchFilter<String> filter) {
        return new ResponseEntity(tipoFiltroPessoaService.searchText(filter.getContent() , filter.getPageable()), OK);
    }
    /**
     * Return an entity,TipoFiltroPessoa ,with an Given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = GET)
    public HttpEntity<TipoFiltroPessoa> get(@PathVariable Long id) {
        return new ResponseEntity(tipoFiltroPessoaService.findOne(id), OK);
    }

    /**
     * Deletes an entity with an given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = DELETE)
    public HttpEntity delete(@PathVariable Long id) {
        tipoFiltroPessoaService.delete(id);
        return new HttpEntity(null);
    }

    /**
     * Simple save or update an entity
     * @param tipoFiltroPessoa
     * @return
     */
    @RequestMapping(method = POST)
    public HttpEntity<TipoFiltroPessoa> save(@RequestBody TipoFiltroPessoa tipoFiltroPessoa) {
        tipoFiltroPessoaService.save(tipoFiltroPessoa);
        return new ResponseEntity(tipoFiltroPessoa, OK);
    }

}
