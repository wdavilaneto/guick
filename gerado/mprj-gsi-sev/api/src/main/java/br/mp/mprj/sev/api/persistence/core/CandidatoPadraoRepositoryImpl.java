/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.CandidatoPadrao;
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

public class CandidatoPadraoRepositoryImpl implements CandidatoPadraoRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (CandidatoPadrao)
     * @param pageable
     * @return
     */
    public Page<CandidatoPadrao> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(CandidatoPadrao.class);
        criteria.addOrder(Order.asc("descricao"));
        return (Page<CandidatoPadrao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<CandidatoPadrao> search(CandidatoPadrao candidatoPadrao, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(CandidatoPadrao.class);

        // Prepare Example
        Example example = Example.create(candidatoPadrao);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        criteria.addOrder(Order.asc("descricao"));
        return (Page<CandidatoPadrao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<CandidatoPadrao> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(CandidatoPadrao.class);
        criteria.add( Restrictions.disjunction()
            .add(Restrictions.ilike("descricao", text, MatchMode.ANYWHERE))
        );
        criteria.addOrder(Order.asc("descricao"));
        return (Page<CandidatoPadrao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public CandidatoPadrao get(Long id) {
        return entityManager.find(CandidatoPadrao.class, id);
    }
    @Override
    public  CandidatoPadrao update( CandidatoPadrao candidatoPadrao){
        Session session = (Session) entityManager.getDelegate();
        session.update(candidatoPadrao);
        return candidatoPadrao;
    }
    @Override
    public  CandidatoPadrao create( CandidatoPadrao candidatoPadrao) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(candidatoPadrao);
        return candidatoPadrao;
    }

}
