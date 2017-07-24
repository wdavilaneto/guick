/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.Eleitor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EleitorRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (Eleitor)
     * @param pageable
     * @return
     */
    public Page<Eleitor> findAll(Pageable pageable);

    Page<Eleitor> search(Eleitor entity, Pageable pageable);

    Page<Eleitor> searchText(String text, Pageable pageable);

    Eleitor get(Long id);

    public Eleitor update(Eleitor eleitor);

    public Eleitor create(Eleitor eleitor);

}
