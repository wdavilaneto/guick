package br.mp.mprj.sev.api.persistence;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.ICoreRepository;
import br.mp.mprj.sev.api.persistence.core.SituacaoEleicaoRepositoryCustom;
import br.mp.mprj.sev.domain.SituacaoEleicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  CRUD Genreated Repository for entitySituacaoEleicao
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Repository
public interface SituacaoEleicaoRepository extends JpaRepository<SituacaoEleicao, Long >, SituacaoEleicaoRepositoryCustom  {

}