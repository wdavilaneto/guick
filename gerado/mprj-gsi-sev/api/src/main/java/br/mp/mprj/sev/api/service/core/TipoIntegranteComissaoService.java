/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.mp.mprj.sev.domain.TipoIntegranteComissao;
import br.mp.mprj.sev.domain.*;
import br.mp.mprj.sev.api.persistence.TipoIntegranteComissaoRepository;
import br.mp.mprj.sev.api.service.ITipoIntegranteComissaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityTipoIntegranteComissao
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class TipoIntegranteComissaoService implements ITipoIntegranteComissaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TipoIntegranteComissaoService.class);

    @Resource(name = "tipoIntegranteComissaoRepository")
    private TipoIntegranteComissaoRepository tipoIntegranteComissaoRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<TipoIntegranteComissao> findAll(Pageable pageable) {
        return tipoIntegranteComissaoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TipoIntegranteComissao> search(TipoIntegranteComissao tipoIntegranteComissao , Pageable pageable) {
        return tipoIntegranteComissaoRepository.search(tipoIntegranteComissao, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TipoIntegranteComissao> searchText(String text , Pageable pageable) {
        return tipoIntegranteComissaoRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoIntegranteComissao findOne(Long id) {
        return tipoIntegranteComissaoRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tipoIntegranteComissaoRepository.delete(id);
    }

    @Override
    @Transactional
    public TipoIntegranteComissao save(TipoIntegranteComissao tipoIntegranteComissao) {
        if (tipoIntegranteComissao.getId() != null){
            return tipoIntegranteComissaoRepository.update(tipoIntegranteComissao);
        }
        return tipoIntegranteComissaoRepository.create(tipoIntegranteComissao);
    }


}
