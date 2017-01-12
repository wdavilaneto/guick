package org.wdn.guick.plugin

import org.hibernate.cfg.Configuration
import org.hibernate.tool.hbm2ddl.SchemaExport
import org.hibernate.tool.hbm2ddl.Target
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.wdn.guick.loader.Jpa
import org.wdn.guick.model.Project

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/28/13
 * Time: 12:02 AM
 * To change this template use File | Settings | File Templates.
 */
@Ignore
class BaseTest {

    static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    @Before
    void setUp() {
        context.scan("org.wdn.guick");
        context.refresh();
    }

    @Test
    void test() {
        // Initializing project path(like done by maven3 plugin or gradle plugin)
        Project project = (Project) context.getBean("project");
        project.initialize("../wdavilaneto")

        Jpa jpa = context.getBean("jpa");
        //jpa.read()

    }

    public void viaSpring(Project project){
//        Map<String, Object> emfProperties = entityManagerFactory.getProperties();
//        println emfProperties
//        println entityManagerFactory.getJpaDialect()
//        println entityManagerFactory.dataSource
//        println entityManagerFactory.getPersistenceProvider()
    }

    public void viaConfiguration(Project project){
        Configuration configuration = new Configuration()
        configuration.setProperty("hibernate.dialect", project.datasource.dialect)
                .setProperty("hibernate.connection.url", project.datasource.url)
                .setProperty("hibernate.connection.username", project.datasource.username)
                .setProperty("hibernate.connection.password", project.datasource.password)
                .setProperty("hibernate.order_updates", "true")

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        resolver.getResource("file://${project.path}/target/classes")

        configuration.addPackage("${project.group}.${project.name}.domain")
        configuration.buildSessionFactory()

        def migrationPath = "${project.path}/src/main/guick/migrate/"
        if (!new File(migrationPath).exists()) {
            new File(migrationPath).mkdirs()
        }
        SchemaExport update = new SchemaExport(configuration)
        update.setOutputFile("${migrationPath}/pendingToDb.sql")
        update.execute(Target.SCRIPT, SchemaExport.Type.CREATE)
    }


}

