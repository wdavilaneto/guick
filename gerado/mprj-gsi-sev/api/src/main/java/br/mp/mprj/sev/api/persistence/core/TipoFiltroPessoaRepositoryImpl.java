/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.api.persistence.core;

import java.math.BigDecimal;
import br.mp.mprj.commons.persistence.PageableHelper;
import br.mp.mprj.sev.domain.TipoFiltroPessoa;
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

public class TipoFiltroPessoaRepositoryImpl implements TipoFiltroPessoaRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (TipoFiltroPessoa)
     * @param pageable
     * @return
     */
    public Page<TipoFiltroPessoa> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(TipoFiltroPessoa.class);
        criteria.addOrder(Order.asc("descricaoTipoFiltro"));
        return (Page<TipoFiltroPessoa>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<TipoFiltroPessoa> search(TipoFiltroPessoa tipoFiltroPessoa, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(TipoFiltroPessoa.class);

        // Prepare Example
        Example example = Example.create(tipoFiltroPessoa);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        if (tipoFiltroPessoa.getEleicao() != null && tipoFiltroPessoa.getEleicao().getId() != null) {
            criteria.add(Restrictions.eq("eleicao.id", tipoFiltroPessoa.getEleicao().getId()));
        }

        criteria.addOrder(Order.asc("descricaoTipoFiltro"));
        return (Page<TipoFiltroPessoa>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<TipoFiltroPessoa> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(TipoFiltroPessoa.class);
        criteria.add( Restrictions.disjunction()
            .add(Restrictions.ilike("descricaoTipoFiltro", text, MatchMode.ANYWHERE))
            .add(Restrictions.ilike("consulta", text, MatchMode.ANYWHERE))
        );
        criteria.addOrder(Order.asc("descricaoTipoFiltro"));
        return (Page<TipoFiltroPessoa>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public TipoFiltroPessoa get(Long id) {
        return entityManager.find(TipoFiltroPessoa.class, id);
    }
    @Override
    public  TipoFiltroPessoa update( TipoFiltroPessoa tipoFiltroPessoa){
        Session session = (Session) entityManager.getDelegate();
        session.update(tipoFiltroPessoa);
        return tipoFiltroPessoa;
    }
    @Override
    public  TipoFiltroPessoa create( TipoFiltroPessoa tipoFiltroPessoa) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(tipoFiltroPessoa);
        return tipoFiltroPessoa;
    }

}
