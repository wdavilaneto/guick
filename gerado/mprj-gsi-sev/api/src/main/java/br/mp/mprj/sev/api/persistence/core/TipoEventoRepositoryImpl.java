/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.TipoEvento;
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

public class TipoEventoRepositoryImpl implements TipoEventoRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (TipoEvento)
     * @param pageable
     * @return
     */
    public Page<TipoEvento> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(TipoEvento.class);
        criteria.addOrder(Order.asc("descricao"));
        return (Page<TipoEvento>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<TipoEvento> search(TipoEvento tipoEvento, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(TipoEvento.class);

        // Prepare Example
        Example example = Example.create(tipoEvento);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        criteria.addOrder(Order.asc("descricao"));
        return (Page<TipoEvento>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<TipoEvento> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(TipoEvento.class);
        criteria.add( Restrictions.disjunction()
            .add(Restrictions.ilike("descricao", text, MatchMode.ANYWHERE))
        );
        criteria.addOrder(Order.asc("descricao"));
        return (Page<TipoEvento>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public TipoEvento get(Long id) {
        return entityManager.find(TipoEvento.class, id);
    }
    @Override
    public  TipoEvento update( TipoEvento tipoEvento){
        Session session = (Session) entityManager.getDelegate();
        session.update(tipoEvento);
        return tipoEvento;
    }
    @Override
    public  TipoEvento create( TipoEvento tipoEvento) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(tipoEvento);
        return tipoEvento;
    }

}
