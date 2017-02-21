/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.SituacaoEleicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SituacaoEleicaoRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (SituacaoEleicao)
     * @param pageable
     * @return
     */
    public Page<SituacaoEleicao> findAll(Pageable pageable);

    Page<SituacaoEleicao> search(SituacaoEleicao entity, Pageable pageable);

    Page<SituacaoEleicao> searchText(String text, Pageable pageable);

    SituacaoEleicao get(Long id);

    public SituacaoEleicao update(SituacaoEleicao situacaoEleicao);

    public SituacaoEleicao create(SituacaoEleicao situacaoEleicao);

}
