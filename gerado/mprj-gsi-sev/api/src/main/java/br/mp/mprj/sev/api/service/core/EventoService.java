/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.Evento;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.EventoRepository;
import br.mp.mprj.sev.api.service.IEventoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityEvento
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class EventoService implements IEventoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventoService.class);

    @Resource(name = "eventoRepository")
    private EventoRepository eventoRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Evento> findAll(Pageable pageable) {
        return eventoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Evento> search(Evento evento , Pageable pageable) {
        return eventoRepository.search(evento, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Evento> searchText(String text , Pageable pageable) {
        return eventoRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Evento findOne(Long id) {
        return eventoRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        eventoRepository.delete(id);
    }

    @Override
    @Transactional
    public Evento save(Evento evento) {
        if (evento.getId() != null){
            return eventoRepository.update(evento);
        }
        return eventoRepository.create(evento);
    }


}
