/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.IntegranteComissao;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.IntegranteComissaoRepository;
import br.mp.mprj.sev.api.service.IIntegranteComissaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityIntegranteComissao
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class IntegranteComissaoService implements IIntegranteComissaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegranteComissaoService.class);

    @Resource(name = "integranteComissaoRepository")
    private IntegranteComissaoRepository integranteComissaoRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<IntegranteComissao> findAll(Pageable pageable) {
        return integranteComissaoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IntegranteComissao> search(IntegranteComissao integranteComissao , Pageable pageable) {
        return integranteComissaoRepository.search(integranteComissao, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IntegranteComissao> searchText(String text , Pageable pageable) {
        return integranteComissaoRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public IntegranteComissao findOne(Long id) {
        return integranteComissaoRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        integranteComissaoRepository.delete(id);
    }

    @Override
    @Transactional
    public IntegranteComissao save(IntegranteComissao integranteComissao) {
        if (integranteComissao.getId() != null){
            return integranteComissaoRepository.update(integranteComissao);
        }
        return integranteComissaoRepository.create(integranteComissao);
    }


}
