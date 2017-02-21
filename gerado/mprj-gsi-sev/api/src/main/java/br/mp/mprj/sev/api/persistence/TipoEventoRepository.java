package br.mp.mprj.sev.api.persistence;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.ICoreRepository;
import br.mp.mprj.sev.api.persistence.core.TipoEventoRepositoryCustom;
import br.mp.mprj.sev.domain.TipoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  CRUD Genreated Repository for entityTipoEvento
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Repository
public interface TipoEventoRepository extends JpaRepository<TipoEvento, Long >, TipoEventoRepositoryCustom  {

}