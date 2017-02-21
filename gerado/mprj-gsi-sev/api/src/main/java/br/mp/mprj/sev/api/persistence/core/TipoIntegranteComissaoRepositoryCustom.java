/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.TipoIntegranteComissao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipoIntegranteComissaoRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (TipoIntegranteComissao)
     * @param pageable
     * @return
     */
    public Page<TipoIntegranteComissao> findAll(Pageable pageable);

    Page<TipoIntegranteComissao> search(TipoIntegranteComissao entity, Pageable pageable);

    Page<TipoIntegranteComissao> searchText(String text, Pageable pageable);

    TipoIntegranteComissao get(Long id);

    public TipoIntegranteComissao update(TipoIntegranteComissao tipoIntegranteComissao);

    public TipoIntegranteComissao create(TipoIntegranteComissao tipoIntegranteComissao);

}
