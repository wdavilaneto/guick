/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.Evento;
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

public class EventoRepositoryImpl implements EventoRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (Evento)
     * @param pageable
     * @return
     */
    public Page<Evento> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Evento.class);
        criteria.addOrder(Order.asc("ocorrencia"));
        return (Page<Evento>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Evento> search(Evento evento, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Evento.class);

        // Prepare Example
        Example example = Example.create(evento);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        if (evento.getEleicao() != null && evento.getEleicao().getId() != null) {
            criteria.add(Restrictions.eq("eleicao.id", evento.getEleicao().getId()));
        }

        if (evento.getTipoEvento() != null && evento.getTipoEvento().getId() != null) {
            criteria.add(Restrictions.eq("tipoEvento.id", evento.getTipoEvento().getId()));
        }

        criteria.addOrder(Order.asc("ocorrencia"));
        return (Page<Evento>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Evento> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Evento.class);
        criteria.add( Restrictions.disjunction()
            .add(Restrictions.ilike("ocorrencia", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("justificativa", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("login", text, MatchMode.ANYWHERE))
        );
        criteria.addOrder(Order.asc("ocorrencia"));
        return (Page<Evento>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Evento get(Long id) {
        return entityManager.find(Evento.class, id);
    }
    @Override
    public  Evento update( Evento evento){
        Session session = (Session) entityManager.getDelegate();
        session.update(evento);
        return evento;
    }
    @Override
    public  Evento create( Evento evento) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(evento);
        return evento;
    }

}
