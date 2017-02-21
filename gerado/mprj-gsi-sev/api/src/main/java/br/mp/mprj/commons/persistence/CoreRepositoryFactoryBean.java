/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.commons.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * The class CoreRepositoryFactoryBean
 */
@SuppressWarnings("unchecked")
public class CoreRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
    extends JpaRepositoryFactoryBean<R, T, I> {

    public CoreRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new CoreRepositoryFactory(entityManager);
    }
    private static class CoreRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {
        private EntityManager entityManager;

        public CoreRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.entityManager = entityManager;
        }

        protected Object getTargetRepository(RepositoryMetadata metadata) {
            return new CoreRepository<T, I>((Class<T>) metadata.getDomainType(), entityManager);
        }
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            // The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
            //to check for QueryDslJpaRepository's which is out of scope.
            return CoreRepository.class;
        }
    }
}