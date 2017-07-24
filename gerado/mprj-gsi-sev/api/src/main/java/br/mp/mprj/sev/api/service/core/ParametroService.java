/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.Parametro;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.ParametroRepository;
import br.mp.mprj.sev.api.service.IParametroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityParametro
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class ParametroService implements IParametroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParametroService.class);

    @Resource(name = "parametroRepository")
    private ParametroRepository parametroRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Parametro> findAll(Pageable pageable) {
        return parametroRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Parametro> search(Parametro parametro , Pageable pageable) {
        return parametroRepository.search(parametro, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Parametro> searchText(String text , Pageable pageable) {
        return parametroRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Parametro findOne(Long id) {
        return parametroRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        parametroRepository.delete(id);
    }

    @Override
    @Transactional
    public Parametro save(Parametro parametro) {
        if (parametro.getId() != null){
            return parametroRepository.update(parametro);
        }
        return parametroRepository.create(parametro);
    }


}
