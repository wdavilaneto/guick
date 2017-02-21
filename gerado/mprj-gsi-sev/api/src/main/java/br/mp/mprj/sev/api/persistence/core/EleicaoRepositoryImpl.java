/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.Eleicao;
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

public class EleicaoRepositoryImpl implements EleicaoRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (Eleicao)
     * @param pageable
     * @return
     */
    public Page<Eleicao> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Eleicao.class);
        criteria.addOrder(Order.asc("nome"));
        return (Page<Eleicao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Eleicao> search(Eleicao eleicao, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Eleicao.class);

        // Prepare Example
        Example example = Example.create(eleicao);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        if (eleicao.getSituacaoEleicao() != null && eleicao.getSituacaoEleicao().getId() != null) {
            criteria.add(Restrictions.eq("situacaoEleicao.id", eleicao.getSituacaoEleicao().getId()));
        }

        criteria.addOrder(Order.asc("nome"));
        return (Page<Eleicao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Eleicao> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Eleicao.class);
        criteria.add( Restrictions.disjunction()
            .add(Restrictions.ilike("nome", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("descricao", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("descricaoOrientacao", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("natureza", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("criterioDesempate", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("textoEmailConvite", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("statusApuracao", text, MatchMode.ANYWHERE))
        );
        criteria.addOrder(Order.asc("nome"));
        return (Page<Eleicao>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Eleicao get(Long id) {
        return entityManager.find(Eleicao.class, id);
    }
    @Override
    public  Eleicao update( Eleicao eleicao){
        Session session = (Session) entityManager.getDelegate();
        session.update(eleicao);
        return eleicao;
    }
    @Override
    public  Eleicao create( Eleicao eleicao) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(eleicao);
        return eleicao;
    }

}
