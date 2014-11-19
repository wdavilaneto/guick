package org.wdn.guick.loader

import org.hibernate.cfg.Configuration
import org.hibernate.ejb.HibernateEntityManager
import org.hibernate.ejb.HibernateEntityManagerFactory
import org.hibernate.tool.hbm2ddl.SchemaExport
import org.hibernate.tool.hbm2ddl.SchemaUpdate
import org.hibernate.tool.hbm2ddl.Target
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.support.GenericXmlApplicationContext
import org.springframework.stereotype.Component
import org.wdn.guick.core.ResourceReader
import org.wdn.guick.model.Project

import javax.annotation.Resource
import javax.persistence.metamodel.ManagedType
import javax.persistence.metamodel.Metamodel
import javax.sql.DataSource

@Component
class Jpa {

    private final Logger logger = LoggerFactory.getLogger(this.class)

    @Resource
    ResourceReader reader

    @Resource
    Project project

    private HibernateEntityManagerFactory entityManagerFactory
    HibernateEntityManager entityManager
    DataSource dataSource
    GenericXmlApplicationContext clientContext

    public Metamodel getMetamodel() {
        return getEntityManager().getMetamodel();
    }

    public void exportAllSchema(String output) {
        Configuration configuration = getConfiguration(getEntityManagerFactory().getProperties());
        SchemaExport schemaExport = new SchemaExport(configuration)
        File file = getFile(output)
        schemaExport.setOutputFile(file.getAbsolutePath())
        schemaExport.execute(Target.SCRIPT, SchemaExport.Type.CREATE)
        println "wrote on  ${file.getAbsolutePath()}"
    }

    public void exportUpdate(String output) {
        Configuration configuration = getConfiguration(getEntityManagerFactory().getProperties());
        if (getDatasource().driver  != null ) {
            configuration.setProperty("hibernate.connection.driver_class", getDatasource().driver.class.name )
        }
        SchemaUpdate schemaUpdate = new SchemaUpdate(configuration)
        File file = getFile(output)
        schemaUpdate.setOutputFile(file.getAbsolutePath())
        schemaUpdate.execute(true, false)
    }

    public void migrate() {
        Configuration configuration = getConfiguration(getEntityManagerFactory().getProperties());
        if (getDatasource().driver  != null ) {
            configuration.setProperty("hibernate.connection.driver_class", getDatasource().driver.class.name )
        }
        SchemaUpdate schemaUpdate = new SchemaUpdate(configuration)
        schemaUpdate.execute(true, true)
    }

    private Configuration getConfiguration(Map props = null) {
        Configuration configuration = new Configuration()
        if (props != null) {
            props.each {
                //
                if (!it.key.equals("hibernate.connection.datasource") && !it.key.equals("hibernate.connection.provider_class")) {
                    configuration.setProperty(it.key, it.value)
                } else {
                    if (it.key.equals("hibernate.connection.datasource")) {

                        configuration.setProperty('hibernate.connection.url', it.value.url)
                        configuration.setProperty('hibernate.connection.username', it.value.username)
                        configuration.setProperty('hibernate.connection.password', it.value.password)
                    }
                }
            }
        }

        for (final ManagedType<?> managedType : getMetamodel().getManagedTypes()) {
            Class<?> entityClass = managedType.getJavaType()
            configuration.addAnnotatedClass(entityClass)
        }
        return configuration
    }

    private File getFile(String output) {
        File file = new File(output)
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs()
        }
        return file
    }

    def debug() {
        println "found ${getMetamodel().getEntities().toString()}"
        for (def e : getMetamodel().getManagedTypes()) {
            println e.getPersistenceType().toString() + ":${e.getJavaType().name} , ${e.getJavaType().simpleName}"
            for (def a : e.getAttributes()) {
                println "    " + [
                        name: a.name,
                        javaType: a.javaType.simpleName,
                        isAssossiation: a.isAssociation(),
                        declaringType: a.declaringType,
                        javaMember: a.javaMember
                ]
                println "       properties: ${a.properties}"
                println "       persistentAttributeType: : ${a.persistentAttributeType.properties}"
                println "       declaringType->Properties: ${a.declaringType.properties}"
            }
        }
    }

    public HibernateEntityManager getEntityManager() {
        if (this.entityManager == null) {
            getEntityManagerFactory()// this creates too
        }
        return entityManager;

    }

    public HibernateEntityManagerFactory getEntityManagerFactory() {
        if (this.entityManagerFactory == null) {
            GenericXmlApplicationContext clientContext = new GenericXmlApplicationContext("datasource.xml");
            this.entityManagerFactory = (HibernateEntityManagerFactory) clientContext.getBean("entityManagerFactory")
            this.entityManager = entityManagerFactory.createEntityManager()
        }
        return entityManagerFactory;
    }

    public DataSource getDatasource() {
        if (!dataSource) {
            dataSource = getSpringContext().getBean("dataSource")
        }
        return dataSource
    }

    public execute(String sql){

    }

    public GenericXmlApplicationContext getSpringContext() {
        if (!clientContext) {
            clientContext = new GenericXmlApplicationContext("datasource.xml");
        }
        return clientContext
    }

}
