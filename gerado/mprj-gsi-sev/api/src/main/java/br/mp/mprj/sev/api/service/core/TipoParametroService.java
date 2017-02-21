/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.TipoParametro;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.TipoParametroRepository;
import br.mp.mprj.sev.api.service.ITipoParametroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityTipoParametro
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class TipoParametroService implements ITipoParametroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TipoParametroService.class);

    @Resource(name = "tipoParametroRepository")
    private TipoParametroRepository tipoParametroRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<TipoParametro> findAll(Pageable pageable) {
        return tipoParametroRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TipoParametro> search(TipoParametro tipoParametro , Pageable pageable) {
        return tipoParametroRepository.search(tipoParametro, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TipoParametro> searchText(String text , Pageable pageable) {
        return tipoParametroRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoParametro findOne(Long id) {
        return tipoParametroRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tipoParametroRepository.delete(id);
    }

    @Override
    @Transactional
    public TipoParametro save(TipoParametro tipoParametro) {
        if (tipoParametro.getId() != null){
            return tipoParametroRepository.update(tipoParametro);
        }
        return tipoParametroRepository.create(tipoParametro);
    }


}
