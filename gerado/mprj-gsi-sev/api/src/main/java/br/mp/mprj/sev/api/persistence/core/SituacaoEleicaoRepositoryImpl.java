/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.SituacaoEleicao;
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

public class SituacaoEleicaoRepositoryImpl implements SituacaoEleicaoRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (SituacaoEleicao)
     * @param pageable
     * @return
     */
    public Page<SituacaoEleicao> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(SituacaoEleicao.class);
        criteria.addOrder(Order.asc("descricaoSituacao"));
        return (Page<SituacaoEleicao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<SituacaoEleicao> search(SituacaoEleicao situacaoEleicao, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(SituacaoEleicao.class);

        // Prepare Example
        Example example = Example.create(situacaoEleicao);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        criteria.addOrder(Order.asc("descricaoSituacao"));
        return (Page<SituacaoEleicao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<SituacaoEleicao> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(SituacaoEleicao.class);
        criteria.add( Restrictions.disjunction()
            .add(Restrictions.ilike("descricaoSituacao", text, MatchMode.ANYWHERE))
        );
        criteria.addOrder(Order.asc("descricaoSituacao"));
        return (Page<SituacaoEleicao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public SituacaoEleicao get(Long id) {
        return entityManager.find(SituacaoEleicao.class, id);
    }
    @Override
    public  SituacaoEleicao update( SituacaoEleicao situacaoEleicao){
        Session session = (Session) entityManager.getDelegate();
        session.update(situacaoEleicao);
        return situacaoEleicao;
    }
    @Override
    public  SituacaoEleicao create( SituacaoEleicao situacaoEleicao) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(situacaoEleicao);
        return situacaoEleicao;
    }

}
