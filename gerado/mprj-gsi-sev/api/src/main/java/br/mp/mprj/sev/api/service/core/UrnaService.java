/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.Urna;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.UrnaRepository;
import br.mp.mprj.sev.api.service.IUrnaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityUrna
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class UrnaService implements IUrnaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrnaService.class);

    @Resource(name = "urnaRepository")
    private UrnaRepository urnaRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Urna> findAll(Pageable pageable) {
        return urnaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Urna> search(Urna urna , Pageable pageable) {
        return urnaRepository.search(urna, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Urna> searchText(String text , Pageable pageable) {
        return urnaRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Urna findOne(Long id) {
        return urnaRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        urnaRepository.delete(id);
    }

    @Override
    @Transactional
    public Urna save(Urna urna) {
        if (urna.getId() != null){
            return urnaRepository.update(urna);
        }
        return urnaRepository.create(urna);
    }


}
