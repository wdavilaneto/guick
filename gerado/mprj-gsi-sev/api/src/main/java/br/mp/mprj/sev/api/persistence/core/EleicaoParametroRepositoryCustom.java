/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.EleicaoParametro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EleicaoParametroRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (EleicaoParametro)
     * @param pageable
     * @return
     */
    public Page<EleicaoParametro> findAll(Pageable pageable);

    Page<EleicaoParametro> search(EleicaoParametro entity, Pageable pageable);

    Page<EleicaoParametro> searchText(String text, Pageable pageable);

    EleicaoParametro get(Long id);

    public EleicaoParametro update(EleicaoParametro eleicaoParametro);

    public EleicaoParametro create(EleicaoParametro eleicaoParametro);

}
