package org.wdn.guick.loader

import org.hibernate.ejb.HibernateEntityManagerFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.support.GenericXmlApplicationContext
import org.springframework.core.io.UrlResource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource
import org.springframework.stereotype.Component
import org.wdn.guick.core.ResourceReader
import org.wdn.guick.model.Project

import javax.annotation.Resource

@Component
class Jpa {

    private final Logger logger = LoggerFactory.getLogger(this.class)

    @Resource
    ResourceReader reader

    @Resource
    Project project

    def read() {
        GenericXmlApplicationContext clientContext = new GenericXmlApplicationContext("datasource.xml");
        AbstractDriverBasedDataSource dataSource = (AbstractDriverBasedDataSource) clientContext.getBean("dataSource")
        HibernateEntityManagerFactory emf = (HibernateEntityManagerFactory) clientContext.getBean("entityManagerFactory")
        println "found ${emf.getMetamodel().getEntities().toString()}"
        for (def e : emf.getMetamodel().getManagedTypes()) {
            println e.getPersistenceType().toString() + ":" + e.getJavaType().toString()
            for (def a : e.getAttributes()) {
                println " ->" + [name: a.name, javaType: a.javaType, javaMember: a.javaMember]
            }
        }
    }

}
