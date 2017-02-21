/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.TipoFiltroPessoa;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.TipoFiltroPessoaRepository;
import br.mp.mprj.sev.api.service.ITipoFiltroPessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityTipoFiltroPessoa
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class TipoFiltroPessoaService implements ITipoFiltroPessoaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TipoFiltroPessoaService.class);

    @Resource(name = "tipoFiltroPessoaRepository")
    private TipoFiltroPessoaRepository tipoFiltroPessoaRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<TipoFiltroPessoa> findAll(Pageable pageable) {
        return tipoFiltroPessoaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TipoFiltroPessoa> search(TipoFiltroPessoa tipoFiltroPessoa , Pageable pageable) {
        return tipoFiltroPessoaRepository.search(tipoFiltroPessoa, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TipoFiltroPessoa> searchText(String text , Pageable pageable) {
        return tipoFiltroPessoaRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoFiltroPessoa findOne(Long id) {
        return tipoFiltroPessoaRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tipoFiltroPessoaRepository.delete(id);
    }

    @Override
    @Transactional
    public TipoFiltroPessoa save(TipoFiltroPessoa tipoFiltroPessoa) {
        if (tipoFiltroPessoa.getId() != null){
            return tipoFiltroPessoaRepository.update(tipoFiltroPessoa);
        }
        return tipoFiltroPessoaRepository.create(tipoFiltroPessoa);
    }


}
