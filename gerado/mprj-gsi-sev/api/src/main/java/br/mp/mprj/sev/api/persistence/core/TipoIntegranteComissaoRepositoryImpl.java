/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.TipoIntegranteComissao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TipoIntegranteComissaoRepositoryImpl implements TipoIntegranteComissaoRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (TipoIntegranteComissao)
     * @param pageable
     * @return
     */
    public Page<TipoIntegranteComissao> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(TipoIntegranteComissao.class);
        criteria.addOrder(Order.asc("nomeTipoIntegrante"));
        return (Page<TipoIntegranteComissao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<TipoIntegranteComissao> search(TipoIntegranteComissao tipoIntegranteComissao, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(TipoIntegranteComissao.class);

        // Prepare Example
        Example example = Example.create(tipoIntegranteComissao);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        criteria.addOrder(Order.asc("nomeTipoIntegrante"));
        return (Page<TipoIntegranteComissao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<TipoIntegranteComissao> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(TipoIntegranteComissao.class);
        criteria.add( Restrictions.disjunction()
            .add(Restrictions.ilike("nomeTipoIntegrante", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("descricaoTipoIntegrante", text, MatchMode.ANYWHERE))
        );
        criteria.addOrder(Order.asc("nomeTipoIntegrante"));
        return (Page<TipoIntegranteComissao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public TipoIntegranteComissao get(Long id) {
        return entityManager.find(TipoIntegranteComissao.class, id);
    }
    @Override
    public  TipoIntegranteComissao update( TipoIntegranteComissao tipoIntegranteComissao){
        Session session = (Session) entityManager.getDelegate();
        session.update(tipoIntegranteComissao);
        return tipoIntegranteComissao;
    }
    @Override
    public  TipoIntegranteComissao create( TipoIntegranteComissao tipoIntegranteComissao) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(tipoIntegranteComissao);
        return tipoIntegranteComissao;
    }

}
