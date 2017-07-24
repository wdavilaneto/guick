/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.ArquivoRelatorio;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.ArquivoRelatorioRepository;
import br.mp.mprj.sev.api.service.IArquivoRelatorioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityArquivoRelatorio
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class ArquivoRelatorioService implements IArquivoRelatorioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArquivoRelatorioService.class);

    @Resource(name = "arquivoRelatorioRepository")
    private ArquivoRelatorioRepository arquivoRelatorioRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ArquivoRelatorio> findAll(Pageable pageable) {
        return arquivoRelatorioRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArquivoRelatorio> search(ArquivoRelatorio arquivoRelatorio , Pageable pageable) {
        return arquivoRelatorioRepository.search(arquivoRelatorio, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArquivoRelatorio> searchText(String text , Pageable pageable) {
        return arquivoRelatorioRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public ArquivoRelatorio findOne(Long id) {
        return arquivoRelatorioRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        arquivoRelatorioRepository.delete(id);
    }

    @Override
    @Transactional
    public ArquivoRelatorio save(ArquivoRelatorio arquivoRelatorio) {
        if (arquivoRelatorio.getId() != null){
            return arquivoRelatorioRepository.update(arquivoRelatorio);
        }
        return arquivoRelatorioRepository.create(arquivoRelatorio);
    }


}
