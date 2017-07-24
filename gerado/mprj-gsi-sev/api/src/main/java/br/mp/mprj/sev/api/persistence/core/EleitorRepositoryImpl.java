/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.Eleitor;
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

public class EleitorRepositoryImpl implements EleitorRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (Eleitor)
     * @param pageable
     * @return
     */
    public Page<Eleitor> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Eleitor.class);
        criteria.addOrder(Order.asc("nome"));
        return (Page<Eleitor>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Eleitor> search(Eleitor eleitor, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Eleitor.class);

        // Prepare Example
        Example example = Example.create(eleitor);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        if (eleitor.getEleicao() != null && eleitor.getEleicao().getId() != null) {
            criteria.add(Restrictions.eq("eleicao.id", eleitor.getEleicao().getId()));
        }

        criteria.addOrder(Order.asc("nome"));
        return (Page<Eleitor>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Eleitor> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Eleitor.class);
        criteria.add( Restrictions.disjunction()
            .add(Restrictions.ilike("cdmatricula", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("codigoAutenticacao", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("enderecoIp", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("nome", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("email", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("descricaoCargo", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("login", text, MatchMode.ANYWHERE))
        );
        criteria.addOrder(Order.asc("nome"));
        return (Page<Eleitor>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Eleitor get(Long id) {
        return entityManager.find(Eleitor.class, id);
    }
    @Override
    public  Eleitor update( Eleitor eleitor){
        Session session = (Session) entityManager.getDelegate();
        session.update(eleitor);
        return eleitor;
    }
    @Override
    public  Eleitor create( Eleitor eleitor) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(eleitor);
        return eleitor;
    }

}
