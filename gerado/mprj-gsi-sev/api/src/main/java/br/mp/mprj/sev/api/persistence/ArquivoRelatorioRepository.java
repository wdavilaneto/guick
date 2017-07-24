package br.mp.mprj.sev.api.persistence;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.ICoreRepository;
import br.mp.mprj.sev.api.persistence.core.ArquivoRelatorioRepositoryCustom;
import br.mp.mprj.sev.domain.ArquivoRelatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  CRUD Genreated Repository for entityArquivoRelatorio
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Repository
public interface ArquivoRelatorioRepository extends JpaRepository<ArquivoRelatorio, Long >, ArquivoRelatorioRepositoryCustom  {

}