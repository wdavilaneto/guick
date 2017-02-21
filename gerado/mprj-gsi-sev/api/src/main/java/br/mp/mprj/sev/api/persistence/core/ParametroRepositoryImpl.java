/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.Parametro;
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

public class ParametroRepositoryImpl implements ParametroRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (Parametro)
     * @param pageable
     * @return
     */
    public Page<Parametro> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Parametro.class);
        criteria.addOrder(Order.asc("descricao"));
        return (Page<Parametro>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Parametro> search(Parametro parametro, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Parametro.class);

        // Prepare Example
        Example example = Example.create(parametro);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        if (parametro.getTipoParametro() != null && parametro.getTipoParametro().getId() != null) {
            criteria.add(Restrictions.eq("tipoParametro.id", parametro.getTipoParametro().getId()));
        }

        criteria.addOrder(Order.asc("descricao"));
        return (Page<Parametro>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Parametro> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Parametro.class);
        criteria.add( Restrictions.disjunction()
            .add(Restrictions.ilike("descricao", text, MatchMode.ANYWHERE))
        );
        criteria.addOrder(Order.asc("descricao"));
        return (Page<Parametro>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Parametro get(Long id) {
        return entityManager.find(Parametro.class, id);
    }
    @Override
    public  Parametro update( Parametro parametro){
        Session session = (Session) entityManager.getDelegate();
        session.update(parametro);
        return parametro;
    }
    @Override
    public  Parametro create( Parametro parametro) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(parametro);
        return parametro;
    }

}
