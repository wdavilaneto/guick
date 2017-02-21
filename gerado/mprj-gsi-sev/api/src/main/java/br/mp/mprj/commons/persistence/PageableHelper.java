/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.commons.persistence;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.data.domain.*;

import java.util.Iterator;
import java.util.List;

/**
 * Created by walter.davila on 09/12/2014.
 */
public class PageableHelper {

    private static PageRequest DEFAULT_PAGEABLE = new PageRequest(0, 10);

    public static Page<?> getPage(Criteria criteria, Pageable pageable){
        // Count
        Long totalItems = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

        // Pageable result Result
        criteria.setProjection(null).setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        if (pageable.getSort() != null) {
            Iterator<Sort.Order> orders = pageable.getSort().iterator();
            while (orders.hasNext()) {
                Sort.Order order = orders.next();
                if (order.getDirection() == null || Sort.Direction.ASC.equals(order.getDirection())) {
                    criteria.addOrder(Order.asc(order.getProperty()));
                } else {
                    criteria.addOrder(Order.desc(order.getProperty()));
                }
            }
        }
        List<?> result = criteria.setFirstResult(pageable.getPageNumber() * pageable.getPageSize()).setMaxResults(pageable.getPageSize()).list();
        return (Page<?>) new PageImpl<>(result, pageable, totalItems);
    }

    public static Pageable deafultPageable(){
        return DEFAULT_PAGEABLE;
    }

}
