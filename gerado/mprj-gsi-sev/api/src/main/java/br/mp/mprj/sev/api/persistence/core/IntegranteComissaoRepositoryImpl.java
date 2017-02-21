/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.IntegranteComissao;
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

public class IntegranteComissaoRepositoryImpl implements IntegranteComissaoRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (IntegranteComissao)
     * @param pageable
     * @return
     */
    public Page<IntegranteComissao> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(IntegranteComissao.class);
        criteria.addOrder(Order.asc("nome"));
        return (Page<IntegranteComissao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<IntegranteComissao> search(IntegranteComissao integranteComissao, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(IntegranteComissao.class);

        // Prepare Example
        Example example = Example.create(integranteComissao);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        if (integranteComissao.getEleicao() != null && integranteComissao.getEleicao().getId() != null) {
            criteria.add(Restrictions.eq("eleicao.id", integranteComissao.getEleicao().getId()));
        }

        if (integranteComissao.getTipoIntegranteComissao() != null && integranteComissao.getTipoIntegranteComissao().getId() != null) {
            criteria.add(Restrictions.eq("tipoIntegranteComissao.id", integranteComissao.getTipoIntegranteComissao().getId()));
        }

        criteria.addOrder(Order.asc("nome"));
        return (Page<IntegranteComissao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<IntegranteComissao> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(IntegranteComissao.class);
        criteria.add( Restrictions.disjunction()
            .add(Restrictions.ilike("cdmatricula", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("nome", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("email", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("login", text, MatchMode.ANYWHERE))
        );
        criteria.addOrder(Order.asc("nome"));
        return (Page<IntegranteComissao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public IntegranteComissao get(Long id) {
        return entityManager.find(IntegranteComissao.class, id);
    }
    @Override
    public  IntegranteComissao update( IntegranteComissao integranteComissao){
        Session session = (Session) entityManager.getDelegate();
        session.update(integranteComissao);
        return integranteComissao;
    }
    @Override
    public  IntegranteComissao create( IntegranteComissao integranteComissao) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(integranteComissao);
        return integranteComissao;
    }

}
