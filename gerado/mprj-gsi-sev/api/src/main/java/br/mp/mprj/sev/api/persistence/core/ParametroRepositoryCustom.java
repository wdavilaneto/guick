/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.Parametro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParametroRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (Parametro)
     * @param pageable
     * @return
     */
    public Page<Parametro> findAll(Pageable pageable);

    Page<Parametro> search(Parametro entity, Pageable pageable);

    Page<Parametro> searchText(String text, Pageable pageable);

    Parametro get(Long id);

    public Parametro update(Parametro parametro);

    public Parametro create(Parametro parametro);

}
