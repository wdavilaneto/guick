/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.TipoRelatorio;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.TipoRelatorioRepository;
import br.mp.mprj.sev.api.service.ITipoRelatorioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityTipoRelatorio
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class TipoRelatorioService implements ITipoRelatorioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TipoRelatorioService.class);

    @Resource(name = "tipoRelatorioRepository")
    private TipoRelatorioRepository tipoRelatorioRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<TipoRelatorio> findAll(Pageable pageable) {
        return tipoRelatorioRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TipoRelatorio> search(TipoRelatorio tipoRelatorio , Pageable pageable) {
        return tipoRelatorioRepository.search(tipoRelatorio, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TipoRelatorio> searchText(String text , Pageable pageable) {
        return tipoRelatorioRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoRelatorio findOne(Long id) {
        return tipoRelatorioRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tipoRelatorioRepository.delete(id);
    }

    @Override
    @Transactional
    public TipoRelatorio save(TipoRelatorio tipoRelatorio) {
        if (tipoRelatorio.getId() != null){
            return tipoRelatorioRepository.update(tipoRelatorio);
        }
        return tipoRelatorioRepository.create(tipoRelatorio);
    }


}
