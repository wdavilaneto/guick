/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.EleicaoParametro;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.EleicaoParametroRepository;
import br.mp.mprj.sev.api.service.IEleicaoParametroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityEleicaoParametro
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class EleicaoParametroService implements IEleicaoParametroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EleicaoParametroService.class);

    @Resource(name = "eleicaoParametroRepository")
    private EleicaoParametroRepository eleicaoParametroRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<EleicaoParametro> findAll(Pageable pageable) {
        return eleicaoParametroRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EleicaoParametro> search(EleicaoParametro eleicaoParametro , Pageable pageable) {
        return eleicaoParametroRepository.search(eleicaoParametro, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EleicaoParametro> searchText(String text , Pageable pageable) {
        return eleicaoParametroRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public EleicaoParametro findOne(Long id) {
        return eleicaoParametroRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        eleicaoParametroRepository.delete(id);
    }

    @Override
    @Transactional
    public EleicaoParametro save(EleicaoParametro eleicaoParametro) {
        if (eleicaoParametro.getId() != null){
            return eleicaoParametroRepository.update(eleicaoParametro);
        }
        return eleicaoParametroRepository.create(eleicaoParametro);
    }


}
