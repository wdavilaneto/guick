/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.ResultadoApuracao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResultadoApuracaoRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (ResultadoApuracao)
     * @param pageable
     * @return
     */
    public Page<ResultadoApuracao> findAll(Pageable pageable);

    Page<ResultadoApuracao> search(ResultadoApuracao entity, Pageable pageable);

    Page<ResultadoApuracao> searchText(String text, Pageable pageable);

    ResultadoApuracao get(Long id);

    public ResultadoApuracao update(ResultadoApuracao resultadoApuracao);

    public ResultadoApuracao create(ResultadoApuracao resultadoApuracao);

}
