/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.Eleicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EleicaoRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (Eleicao)
     * @param pageable
     * @return
     */
    public Page<Eleicao> findAll(Pageable pageable);

    Page<Eleicao> search(Eleicao entity, Pageable pageable);

    Page<Eleicao> searchText(String text, Pageable pageable);

    Eleicao get(Long id);

    public Eleicao update(Eleicao eleicao);

    public Eleicao create(Eleicao eleicao);

}
