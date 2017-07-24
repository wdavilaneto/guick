/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.CandidatoPadrao;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.CandidatoPadraoRepository;
import br.mp.mprj.sev.api.service.ICandidatoPadraoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityCandidatoPadrao
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class CandidatoPadraoService implements ICandidatoPadraoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CandidatoPadraoService.class);

    @Resource(name = "candidatoPadraoRepository")
    private CandidatoPadraoRepository candidatoPadraoRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<CandidatoPadrao> findAll(Pageable pageable) {
        return candidatoPadraoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CandidatoPadrao> search(CandidatoPadrao candidatoPadrao , Pageable pageable) {
        return candidatoPadraoRepository.search(candidatoPadrao, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CandidatoPadrao> searchText(String text , Pageable pageable) {
        return candidatoPadraoRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public CandidatoPadrao findOne(Long id) {
        return candidatoPadraoRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        candidatoPadraoRepository.delete(id);
    }

    @Override
    @Transactional
    public CandidatoPadrao save(CandidatoPadrao candidatoPadrao) {
        if (candidatoPadrao.getId() != null){
            return candidatoPadraoRepository.update(candidatoPadrao);
        }
        return candidatoPadraoRepository.create(candidatoPadrao);
    }


}
