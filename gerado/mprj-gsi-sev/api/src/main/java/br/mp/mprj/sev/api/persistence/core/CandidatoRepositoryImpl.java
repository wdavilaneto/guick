/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.Candidato;
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

public class CandidatoRepositoryImpl implements CandidatoRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (Candidato)
     * @param pageable
     * @return
     */
    public Page<Candidato> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Candidato.class);
        criteria.addOrder(Order.asc("nome"));
        return (Page<Candidato>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Candidato> search(Candidato candidato, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Candidato.class);

        // Prepare Example
        Example example = Example.create(candidato);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        if (candidato.getCandidatoPadrao() != null && candidato.getCandidatoPadrao().getId() != null) {
            criteria.add(Restrictions.eq("candidatoPadrao.id", candidato.getCandidatoPadrao().getId()));
        }

        if (candidato.getEleicao() != null && candidato.getEleicao().getId() != null) {
            criteria.add(Restrictions.eq("eleicao.id", candidato.getEleicao().getId()));
        }

        criteria.addOrder(Order.asc("nome"));
        return (Page<Candidato>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Candidato> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Candidato.class);
        criteria.add( Restrictions.disjunction()
            .add(Restrictions.ilike("cdmatricula", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("nome", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("email", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("nomeCompleto", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("login", text, MatchMode.ANYWHERE))
        );
        criteria.addOrder(Order.asc("nome"));
        return (Page<Candidato>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Candidato get(Long id) {
        return entityManager.find(Candidato.class, id);
    }
    @Override
    public  Candidato update( Candidato candidato){
        Session session = (Session) entityManager.getDelegate();
        session.update(candidato);
        return candidato;
    }
    @Override
    public  Candidato create( Candidato candidato) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(candidato);
        return candidato;
    }

}
