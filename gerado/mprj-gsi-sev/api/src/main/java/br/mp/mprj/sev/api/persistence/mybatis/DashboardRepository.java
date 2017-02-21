/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.mybatis;

import br.mp.mprj.sev.domain.dto.HistogramDto;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by walter on 14/12/14.
 */
public interface DashboardRepository {

    @Transactional(readOnly = true)
    List<HistogramDto> allCount();

    @Transactional(readOnly = true)
    List<HistogramDto> getResultadoApuracaoCollectionWithSumCandidatoQtVotos();
    @Transactional(readOnly = true)
    List<HistogramDto> getCandidatoCollectionWithSumEleicaoCriterioDesempate();
    @Transactional(readOnly = true)
    List<HistogramDto> getEleitorCollectionWithSumEleicaoVersao();
    @Transactional(readOnly = true)
    List<HistogramDto> getUrnaCollectionWithSumEleicaoVersao();

}
