/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.TipoParametro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipoParametroRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (TipoParametro)
     * @param pageable
     * @return
     */
    public Page<TipoParametro> findAll(Pageable pageable);

    Page<TipoParametro> search(TipoParametro entity, Pageable pageable);

    Page<TipoParametro> searchText(String text, Pageable pageable);

    TipoParametro get(Long id);

    public TipoParametro update(TipoParametro tipoParametro);

    public TipoParametro create(TipoParametro tipoParametro);

}
