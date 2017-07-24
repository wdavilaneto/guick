/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.Cedula;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.CedulaRepository;
import br.mp.mprj.sev.api.service.ICedulaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityCedula
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class CedulaService implements ICedulaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CedulaService.class);

    @Resource(name = "cedulaRepository")
    private CedulaRepository cedulaRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Cedula> findAll(Pageable pageable) {
        return cedulaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cedula> search(Cedula cedula , Pageable pageable) {
        return cedulaRepository.search(cedula, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cedula> searchText(String text , Pageable pageable) {
        return cedulaRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cedula findOne(String id) {
        return cedulaRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(String id) {
        cedulaRepository.delete(id);
    }

    @Override
    @Transactional
    public Cedula save(Cedula cedula) {
        if (cedula.getId() != null){
            return cedulaRepository.update(cedula);
        }
        return cedulaRepository.create(cedula);
    }


}
