/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.ArquivoRelatorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArquivoRelatorioRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (ArquivoRelatorio)
     * @param pageable
     * @return
     */
    public Page<ArquivoRelatorio> findAll(Pageable pageable);

    Page<ArquivoRelatorio> search(ArquivoRelatorio entity, Pageable pageable);

    Page<ArquivoRelatorio> searchText(String text, Pageable pageable);

    ArquivoRelatorio get(Long id);

    public ArquivoRelatorio update(ArquivoRelatorio arquivoRelatorio);

    public ArquivoRelatorio create(ArquivoRelatorio arquivoRelatorio);

}
