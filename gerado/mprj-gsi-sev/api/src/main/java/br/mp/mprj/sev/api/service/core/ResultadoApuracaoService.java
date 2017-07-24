/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.ResultadoApuracao;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.ResultadoApuracaoRepository;
import br.mp.mprj.sev.api.service.IResultadoApuracaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityResultadoApuracao
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class ResultadoApuracaoService implements IResultadoApuracaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultadoApuracaoService.class);

    @Resource(name = "resultadoApuracaoRepository")
    private ResultadoApuracaoRepository resultadoApuracaoRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ResultadoApuracao> findAll(Pageable pageable) {
        return resultadoApuracaoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResultadoApuracao> search(ResultadoApuracao resultadoApuracao , Pageable pageable) {
        return resultadoApuracaoRepository.search(resultadoApuracao, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResultadoApuracao> searchText(String text , Pageable pageable) {
        return resultadoApuracaoRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public ResultadoApuracao findOne(Long id) {
        return resultadoApuracaoRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        resultadoApuracaoRepository.delete(id);
    }

    @Override
    @Transactional
    public ResultadoApuracao save(ResultadoApuracao resultadoApuracao) {
        if (resultadoApuracao.getId() != null){
            return resultadoApuracaoRepository.update(resultadoApuracao);
        }
        return resultadoApuracaoRepository.create(resultadoApuracao);
    }


}
