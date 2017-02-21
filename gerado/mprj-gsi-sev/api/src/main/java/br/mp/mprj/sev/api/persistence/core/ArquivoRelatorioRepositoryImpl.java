/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.ArquivoRelatorio;
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

public class ArquivoRelatorioRepositoryImpl implements ArquivoRelatorioRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (ArquivoRelatorio)
     * @param pageable
     * @return
     */
    public Page<ArquivoRelatorio> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(ArquivoRelatorio.class);
        criteria.addOrder(Order.asc("id"));
        return (Page<ArquivoRelatorio>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<ArquivoRelatorio> search(ArquivoRelatorio arquivoRelatorio, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(ArquivoRelatorio.class);

        // Prepare Example
        Example example = Example.create(arquivoRelatorio);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        if (arquivoRelatorio.getEleicao() != null && arquivoRelatorio.getEleicao().getId() != null) {
            criteria.add(Restrictions.eq("eleicao.id", arquivoRelatorio.getEleicao().getId()));
        }

        if (arquivoRelatorio.getEvento() != null && arquivoRelatorio.getEvento().getId() != null) {
            criteria.add(Restrictions.eq("evento.id", arquivoRelatorio.getEvento().getId()));
        }

        if (arquivoRelatorio.getTipoRelatorio() != null && arquivoRelatorio.getTipoRelatorio().getId() != null) {
            criteria.add(Restrictions.eq("tipoRelatorio.id", arquivoRelatorio.getTipoRelatorio().getId()));
        }

        criteria.addOrder(Order.asc("id"));
        return (Page<ArquivoRelatorio>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<ArquivoRelatorio> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(ArquivoRelatorio.class);
        criteria.add( Restrictions.disjunction()
        );
        criteria.addOrder(Order.asc("id"));
        return (Page<ArquivoRelatorio>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public ArquivoRelatorio get(Long id) {
        return entityManager.find(ArquivoRelatorio.class, id);
    }
    @Override
    public  ArquivoRelatorio update( ArquivoRelatorio arquivoRelatorio){
        Session session = (Session) entityManager.getDelegate();
        session.update(arquivoRelatorio);
        return arquivoRelatorio;
    }
    @Override
    public  ArquivoRelatorio create( ArquivoRelatorio arquivoRelatorio) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(arquivoRelatorio);
        return arquivoRelatorio;
    }

}
