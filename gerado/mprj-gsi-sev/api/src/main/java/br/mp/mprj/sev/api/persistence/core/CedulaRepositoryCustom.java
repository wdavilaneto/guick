/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.Cedula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CedulaRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (Cedula)
     * @param pageable
     * @return
     */
    public Page<Cedula> findAll(Pageable pageable);

    Page<Cedula> search(Cedula entity, Pageable pageable);

    Page<Cedula> searchText(String text, Pageable pageable);

    Cedula get(String id);

    public Cedula update(Cedula cedula);

    public Cedula create(Cedula cedula);

}
