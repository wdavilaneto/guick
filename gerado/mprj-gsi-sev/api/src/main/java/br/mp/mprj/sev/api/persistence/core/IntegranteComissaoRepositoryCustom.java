/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.IntegranteComissao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntegranteComissaoRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (IntegranteComissao)
     * @param pageable
     * @return
     */
    public Page<IntegranteComissao> findAll(Pageable pageable);

    Page<IntegranteComissao> search(IntegranteComissao entity, Pageable pageable);

    Page<IntegranteComissao> searchText(String text, Pageable pageable);

    IntegranteComissao get(Long id);

    public IntegranteComissao update(IntegranteComissao integranteComissao);

    public IntegranteComissao create(IntegranteComissao integranteComissao);

}
