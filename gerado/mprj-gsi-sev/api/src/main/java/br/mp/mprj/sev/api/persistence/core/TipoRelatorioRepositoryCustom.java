/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.TipoRelatorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipoRelatorioRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (TipoRelatorio)
     * @param pageable
     * @return
     */
    public Page<TipoRelatorio> findAll(Pageable pageable);

    Page<TipoRelatorio> search(TipoRelatorio entity, Pageable pageable);

    Page<TipoRelatorio> searchText(String text, Pageable pageable);

    TipoRelatorio get(Long id);

    public TipoRelatorio update(TipoRelatorio tipoRelatorio);

    public TipoRelatorio create(TipoRelatorio tipoRelatorio);

}
