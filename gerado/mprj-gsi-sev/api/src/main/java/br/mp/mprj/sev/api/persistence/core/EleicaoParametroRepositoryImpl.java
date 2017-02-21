/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.EleicaoParametro;
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

public class EleicaoParametroRepositoryImpl implements EleicaoParametroRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (EleicaoParametro)
     * @param pageable
     * @return
     */
    public Page<EleicaoParametro> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(EleicaoParametro.class);
        criteria.addOrder(Order.asc("valorParametro"));
        return (Page<EleicaoParametro>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<EleicaoParametro> search(EleicaoParametro eleicaoParametro, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(EleicaoParametro.class);

        // Prepare Example
        Example example = Example.create(eleicaoParametro);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        if (eleicaoParametro.getEleicao() != null && eleicaoParametro.getEleicao().getId() != null) {
            criteria.add(Restrictions.eq("eleicao.id", eleicaoParametro.getEleicao().getId()));
        }

        if (eleicaoParametro.getParametro() != null && eleicaoParametro.getParametro().getId() != null) {
            criteria.add(Restrictions.eq("parametro.id", eleicaoParametro.getParametro().getId()));
        }

        criteria.addOrder(Order.asc("valorParametro"));
        return (Page<EleicaoParametro>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<EleicaoParametro> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(EleicaoParametro.class);
        criteria.add( Restrictions.disjunction()
            .add(Restrictions.ilike("valorParametro", text, MatchMode.ANYWHERE))
        );
        criteria.addOrder(Order.asc("valorParametro"));
        return (Page<EleicaoParametro>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public EleicaoParametro get(Long id) {
        return entityManager.find(EleicaoParametro.class, id);
    }
    @Override
    public  EleicaoParametro update( EleicaoParametro eleicaoParametro){
        Session session = (Session) entityManager.getDelegate();
        session.update(eleicaoParametro);
        return eleicaoParametro;
    }
    @Override
    public  EleicaoParametro create( EleicaoParametro eleicaoParametro) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(eleicaoParametro);
        return eleicaoParametro;
    }

}
