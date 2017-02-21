/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mprj.sev.domain.TipoFiltroPessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipoFiltroPessoaRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (TipoFiltroPessoa)
     * @param pageable
     * @return
     */
    public Page<TipoFiltroPessoa> findAll(Pageable pageable);

    Page<TipoFiltroPessoa> search(TipoFiltroPessoa entity, Pageable pageable);

    Page<TipoFiltroPessoa> searchText(String text, Pageable pageable);

    TipoFiltroPessoa get(Long id);

    public TipoFiltroPessoa update(TipoFiltroPessoa tipoFiltroPessoa);

    public TipoFiltroPessoa create(TipoFiltroPessoa tipoFiltroPessoa);

}
