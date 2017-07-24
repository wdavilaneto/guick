/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.Urna;
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

public class UrnaRepositoryImpl implements UrnaRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (Urna)
     * @param pageable
     * @return
     */
    public Page<Urna> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Urna.class);
        criteria.addOrder(Order.asc("msgComposta"));
        return (Page<Urna>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Urna> search(Urna urna, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Urna.class);

        // Prepare Example
        Example example = Example.create(urna);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        if (urna.getEleicao() != null && urna.getEleicao().getId() != null) {
            criteria.add(Restrictions.eq("eleicao.id", urna.getEleicao().getId()));
        }

        criteria.addOrder(Order.asc("msgComposta"));
        return (Page<Urna>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Urna> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Urna.class);
        criteria.add( Restrictions.disjunction()
            .add(Restrictions.ilike("msgComposta", text, MatchMode.ANYWHERE))
        );
        criteria.addOrder(Order.asc("msgComposta"));
        return (Page<Urna>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Urna get(Long id) {
        return entityManager.find(Urna.class, id);
    }
    @Override
    public  Urna update( Urna urna){
        Session session = (Session) entityManager.getDelegate();
        session.update(urna);
        return urna;
    }
    @Override
    public  Urna create( Urna urna) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(urna);
        return urna;
    }

}
