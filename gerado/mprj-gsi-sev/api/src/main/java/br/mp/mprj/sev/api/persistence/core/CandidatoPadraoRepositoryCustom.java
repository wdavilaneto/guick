/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.CandidatoPadrao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CandidatoPadraoRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (CandidatoPadrao)
     * @param pageable
     * @return
     */
    public Page<CandidatoPadrao> findAll(Pageable pageable);

    Page<CandidatoPadrao> search(CandidatoPadrao entity, Pageable pageable);

    Page<CandidatoPadrao> searchText(String text, Pageable pageable);

    CandidatoPadrao get(Long id);

    public CandidatoPadrao update(CandidatoPadrao candidatoPadrao);

    public CandidatoPadrao create(CandidatoPadrao candidatoPadrao);

}
