/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.Cedula;
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

public class CedulaRepositoryImpl implements CedulaRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (Cedula)
     * @param pageable
     * @return
     */
    public Page<Cedula> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Cedula.class);
        criteria.addOrder(Order.asc("msgCriptografada"));
        return (Page<Cedula>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Cedula> search(Cedula cedula, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Cedula.class);

        // Prepare Example
        Example example = Example.create(cedula);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        if (cedula.getUrna() != null && cedula.getUrna().getId() != null) {
            criteria.add(Restrictions.eq("urna.id", cedula.getUrna().getId()));
        }

        criteria.addOrder(Order.asc("msgCriptografada"));
        return (Page<Cedula>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Cedula> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Cedula.class);
        criteria.add( Restrictions.disjunction()
            .add(Restrictions.ilike("msgCriptografada", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("calculada", text, MatchMode.ANYWHERE))
        );
        criteria.addOrder(Order.asc("msgCriptografada"));
        return (Page<Cedula>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Cedula get(String id) {
        return entityManager.find(Cedula.class, id);
    }
    @Override
    public  Cedula update( Cedula cedula){
        Session session = (Session) entityManager.getDelegate();
        session.update(cedula);
        return cedula;
    }
    @Override
    public  Cedula create( Cedula cedula) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(cedula);
        return cedula;
    }

}
