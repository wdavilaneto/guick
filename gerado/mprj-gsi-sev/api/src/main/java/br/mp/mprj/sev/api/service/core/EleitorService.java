/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.Eleitor;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.EleitorRepository;
import br.mp.mprj.sev.api.service.IEleitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityEleitor
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class EleitorService implements IEleitorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EleitorService.class);

    @Resource(name = "eleitorRepository")
    private EleitorRepository eleitorRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Eleitor> findAll(Pageable pageable) {
        return eleitorRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Eleitor> search(Eleitor eleitor , Pageable pageable) {
        return eleitorRepository.search(eleitor, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Eleitor> searchText(String text , Pageable pageable) {
        return eleitorRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Eleitor findOne(Long id) {
        return eleitorRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        eleitorRepository.delete(id);
    }

    @Override
    @Transactional
    public Eleitor save(Eleitor eleitor) {
        if (eleitor.getId() != null){
            return eleitorRepository.update(eleitor);
        }
        return eleitorRepository.create(eleitor);
    }


}
