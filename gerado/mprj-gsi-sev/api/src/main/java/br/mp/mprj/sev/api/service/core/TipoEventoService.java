/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.TipoEvento;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.TipoEventoRepository;
import br.mp.mprj.sev.api.service.ITipoEventoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityTipoEvento
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class TipoEventoService implements ITipoEventoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TipoEventoService.class);

    @Resource(name = "tipoEventoRepository")
    private TipoEventoRepository tipoEventoRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<TipoEvento> findAll(Pageable pageable) {
        return tipoEventoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TipoEvento> search(TipoEvento tipoEvento , Pageable pageable) {
        return tipoEventoRepository.search(tipoEvento, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TipoEvento> searchText(String text , Pageable pageable) {
        return tipoEventoRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoEvento findOne(Long id) {
        return tipoEventoRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tipoEventoRepository.delete(id);
    }

    @Override
    @Transactional
    public TipoEvento save(TipoEvento tipoEvento) {
        if (tipoEvento.getId() != null){
            return tipoEventoRepository.update(tipoEvento);
        }
        return tipoEventoRepository.create(tipoEvento);
    }


}
