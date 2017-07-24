/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.Eleicao;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.EleicaoRepository;
import br.mp.mprj.sev.api.service.IEleicaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityEleicao
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class EleicaoService implements IEleicaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EleicaoService.class);

    @Resource(name = "eleicaoRepository")
    private EleicaoRepository eleicaoRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Eleicao> findAll(Pageable pageable) {
        return eleicaoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Eleicao> search(Eleicao eleicao , Pageable pageable) {
        return eleicaoRepository.search(eleicao, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Eleicao> searchText(String text , Pageable pageable) {
        return eleicaoRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Eleicao findOne(Long id) {
        return eleicaoRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        eleicaoRepository.delete(id);
    }

    @Override
    @Transactional
    public Eleicao save(Eleicao eleicao) {
        if (eleicao.getId() != null){
            return eleicaoRepository.update(eleicao);
        }
        return eleicaoRepository.create(eleicao);
    }


}
