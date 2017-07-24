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
import br.mp.mprj.sev.domain.ResultadoApuracao;
import br.mp.mprj.sev.api.service.IResultadoApuracaoService;
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
 *  CRUD Rest Json 'Controller' for entityResultadoApuracao
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@RestController
@RequestMapping(value="/resultadoApuracao")
public class ResultadoApuracaoController {

    private static PageRequest DEFAULT_PAGE = new PageRequest(0,20);
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultadoApuracaoController.class);

    @Resource(name = "resultadoApuracaoService")
    private IResultadoApuracaoService resultadoApuracaoService;

    /**
     * Returns an full, but Paged, list of all entities (ResultadoApuracao)
     * @param pagination
     * @return
     */
    @RequestMapping(method = GET)
    public HttpEntity<Page<ResultadoApuracao>> findAll(Pagination pagination) {
        return new ResponseEntity(resultadoApuracaoService.findAll(pagination.getPageable()), OK);
    }

    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/search", method = POST)
    public HttpEntity<Page<ResultadoApuracao>> search(@RequestBody SearchFilter<ResultadoApuracao> filter) {
        return new ResponseEntity(resultadoApuracaoService.search(filter.getContent() , filter.getPageable()), OK);
    }

    /**
     * Request first page of a text based search on all fields ignoring associations
     * @param text
     * @return
     */
    @RequestMapping(value = "/searchText", method = GET)
    public HttpEntity<Page<ResultadoApuracao>> searchTextGet(String text) {
        return new ResponseEntity(resultadoApuracaoService.searchText(text , DEFAULT_PAGE ), OK);
    }
    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/searchText", method = RequestMethod.POST)
    public HttpEntity<Page<ResultadoApuracao>> searchText(@RequestBody SearchFilter<String> filter) {
        return new ResponseEntity(resultadoApuracaoService.searchText(filter.getContent() , filter.getPageable()), OK);
    }
    /**
     * Return an entity,ResultadoApuracao ,with an Given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = GET)
    public HttpEntity<ResultadoApuracao> get(@PathVariable Long id) {
        return new ResponseEntity(resultadoApuracaoService.findOne(id), OK);
    }

    /**
     * Deletes an entity with an given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = DELETE)
    public HttpEntity delete(@PathVariable Long id) {
        resultadoApuracaoService.delete(id);
        return new HttpEntity(null);
    }

    /**
     * Simple save or update an entity
     * @param resultadoApuracao
     * @return
     */
    @RequestMapping(method = POST)
    public HttpEntity<ResultadoApuracao> save(@RequestBody ResultadoApuracao resultadoApuracao) {
        resultadoApuracaoService.save(resultadoApuracao);
        return new ResponseEntity(resultadoApuracao, OK);
    }

}
