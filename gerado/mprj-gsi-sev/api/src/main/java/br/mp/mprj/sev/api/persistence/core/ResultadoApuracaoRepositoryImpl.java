/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.ResultadoApuracao;
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

public class ResultadoApuracaoRepositoryImpl implements ResultadoApuracaoRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (ResultadoApuracao)
     * @param pageable
     * @return
     */
    public Page<ResultadoApuracao> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(ResultadoApuracao.class);
        criteria.addOrder(Order.asc("id"));
        return (Page<ResultadoApuracao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<ResultadoApuracao> search(ResultadoApuracao resultadoApuracao, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(ResultadoApuracao.class);

        // Prepare Example
        Example example = Example.create(resultadoApuracao);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        if (resultadoApuracao.getCandidato() != null && resultadoApuracao.getCandidato().getId() != null) {
            criteria.add(Restrictions.eq("candidato.id", resultadoApuracao.getCandidato().getId()));
        }

        criteria.addOrder(Order.asc("id"));
        return (Page<ResultadoApuracao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<ResultadoApuracao> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(ResultadoApuracao.class);
        criteria.add( Restrictions.disjunction()
        );
        criteria.addOrder(Order.asc("id"));
        return (Page<ResultadoApuracao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public ResultadoApuracao get(Long id) {
        return entityManager.find(ResultadoApuracao.class, id);
    }
    @Override
    public  ResultadoApuracao update( ResultadoApuracao resultadoApuracao){
        Session session = (Session) entityManager.getDelegate();
        session.update(resultadoApuracao);
        return resultadoApuracao;
    }
    @Override
    public  ResultadoApuracao create( ResultadoApuracao resultadoApuracao) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(resultadoApuracao);
        return resultadoApuracao;
    }

}
