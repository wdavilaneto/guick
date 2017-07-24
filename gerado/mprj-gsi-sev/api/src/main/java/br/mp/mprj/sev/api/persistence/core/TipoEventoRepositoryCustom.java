/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.TipoEvento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipoEventoRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (TipoEvento)
     * @param pageable
     * @return
     */
    public Page<TipoEvento> findAll(Pageable pageable);

    Page<TipoEvento> search(TipoEvento entity, Pageable pageable);

    Page<TipoEvento> searchText(String text, Pageable pageable);

    TipoEvento get(Long id);

    public TipoEvento update(TipoEvento tipoEvento);

    public TipoEvento create(TipoEvento tipoEvento);

}
