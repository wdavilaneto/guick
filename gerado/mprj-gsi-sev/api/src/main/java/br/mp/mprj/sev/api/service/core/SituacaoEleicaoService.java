/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.SituacaoEleicao;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.SituacaoEleicaoRepository;
import br.mp.mprj.sev.api.service.ISituacaoEleicaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entitySituacaoEleicao
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class SituacaoEleicaoService implements ISituacaoEleicaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SituacaoEleicaoService.class);

    @Resource(name = "situacaoEleicaoRepository")
    private SituacaoEleicaoRepository situacaoEleicaoRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<SituacaoEleicao> findAll(Pageable pageable) {
        return situacaoEleicaoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SituacaoEleicao> search(SituacaoEleicao situacaoEleicao , Pageable pageable) {
        return situacaoEleicaoRepository.search(situacaoEleicao, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SituacaoEleicao> searchText(String text , Pageable pageable) {
        return situacaoEleicaoRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public SituacaoEleicao findOne(Long id) {
        return situacaoEleicaoRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        situacaoEleicaoRepository.delete(id);
    }

    @Override
    @Transactional
    public SituacaoEleicao save(SituacaoEleicao situacaoEleicao) {
        if (situacaoEleicao.getId() != null){
            return situacaoEleicaoRepository.update(situacaoEleicao);
        }
        return situacaoEleicaoRepository.create(situacaoEleicao);
    }


}
