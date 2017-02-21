/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.Urna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UrnaRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (Urna)
     * @param pageable
     * @return
     */
    public Page<Urna> findAll(Pageable pageable);

    Page<Urna> search(Urna entity, Pageable pageable);

    Page<Urna> searchText(String text, Pageable pageable);

    Urna get(Long id);

    public Urna update(Urna urna);

    public Urna create(Urna urna);

}
