/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.Candidato;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.CandidatoRepository;
import br.mp.mprj.sev.api.service.ICandidatoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityCandidato
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class CandidatoService implements ICandidatoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CandidatoService.class);

    @Resource(name = "candidatoRepository")
    private CandidatoRepository candidatoRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Candidato> findAll(Pageable pageable) {
        return candidatoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Candidato> search(Candidato candidato , Pageable pageable) {
        return candidatoRepository.search(candidato, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Candidato> searchText(String text , Pageable pageable) {
        return candidatoRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Candidato findOne(Long id) {
        return candidatoRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        candidatoRepository.delete(id);
    }

    @Override
    @Transactional
    public Candidato save(Candidato candidato) {
        if (candidato.getId() != null){
            return candidatoRepository.update(candidato);
        }
        return candidatoRepository.create(candidato);
    }


}
