/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventoRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (Evento)
     * @param pageable
     * @return
     */
    public Page<Evento> findAll(Pageable pageable);

    Page<Evento> search(Evento entity, Pageable pageable);

    Page<Evento> searchText(String text, Pageable pageable);

    Evento get(Long id);

    public Evento update(Evento evento);

    public Evento create(Evento evento);

}
