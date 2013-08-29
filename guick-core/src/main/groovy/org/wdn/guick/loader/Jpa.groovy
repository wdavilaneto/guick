package org.wdn.guick.loader

import groovy.transform.CompileStatic
import org.hibernate.ejb.HibernateEntityManagerFactory
import org.springframework.context.support.GenericXmlApplicationContext
import org.springframework.core.io.UrlResource
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource
import org.springframework.stereotype.Component
import org.wdn.guick.core.ResourceReader
import org.wdn.guick.model.Project

import javax.annotation.Resource

@Component
@CompileStatic
class Jpa {

    @Resource
    ResourceReader reader

    @Resource
    Project project

    def read() {
        GenericXmlApplicationContext clientContext

        try {
            Reader uri = reader.getResource("datasource.xml");
            clientContext = new GenericXmlApplicationContext("datasource.xml");
        } catch (Throwable e) {
            UrlResource resource = new UrlResource("file://${project.path}/src/main/resources/datasource.xml");
            clientContext = new GenericXmlApplicationContext(resource);
        }
        AbstractDriverBasedDataSource dataSource = (AbstractDriverBasedDataSource)clientContext.getBean("dataSource")

        HibernateEntityManagerFactory emf = (HibernateEntityManagerFactory) clientContext.getBean("entityManagerFactory")

        println emf.getMetamodel().getEntities()
        println emf.getMetamodel().getEmbeddables()
        println emf.getMetamodel().getManagedTypes()

    }

}
