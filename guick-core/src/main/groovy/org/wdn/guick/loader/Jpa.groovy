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

        UrlResource resource
        //resource = new UrlResource(new URL("file", null, "${project.path}/src/main/resources/datasource.xml"));
        clientContext = new GenericXmlApplicationContext("classpath:datasource.xml");

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
