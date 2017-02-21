/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.Candidato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CandidatoRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (Candidato)
     * @param pageable
     * @return
     */
    public Page<Candidato> findAll(Pageable pageable);

    Page<Candidato> search(Candidato entity, Pageable pageable);

    Page<Candidato> searchText(String text, Pageable pageable);

    Candidato get(Long id);

    public Candidato update(Candidato candidato);

    public Candidato create(Candidato candidato);

}
