package org.wdn.guick.loader

import com.jolbox.bonecp.BoneCPDataSource
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component
import org.wdn.guick.model.Entity
import org.wdn.guick.model.Project
import org.wdn.guick.model.Table
import org.wdn.guick.support.BusinessRulesManager
import org.wdn.guick.support.Table2EntityConverter

import javax.annotation.Resource
import javax.sql.DataSource

/**
 * Created with IntelliJ IDEA.
 * User: wdavilaneto
 * Date: 2/27/14
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
class Database {

    private String DEFAULT_ENVIRONMENT = "development"

    private final Logger logger = LoggerFactory.getLogger(this.class)

    @Resource
    private TableRetriever tableRetriever

    String driverName, url, user, password

    @Resource
    Project project

    @Resource Table2EntityConverter entityConverter

    @Resource
    private BusinessRulesManager businessRulesManager;

    @Autowired
    @Lazy
    private Environment env

    @Autowired
    private ResourceLoader resourceLoader

    public List<Table> loadTables(def owners = null) {
        List<Table> tables = tableRetriever.execute(createSession(), owners == null ? user : owners)
        project.tables.clear()
        project.tables.addAll(tables)
        return tables
    }

    public List<Entity> generateModel() {
        // Fixem merge? ?
        project.entities = entityConverter.generateModel(loadTables());
        businessRulesManager.handleRules(project);
        return project.entities
    }

    private DataSource getDatasource() {

        if (driverName != url && url != user && url != null) {
            return new BoneCPDataSource(driverClass: driverName, jdbcUrl: url, username: user, password: password);
        }
        if (project.database != null && project.database.url != null) {
            user = project.database.user
            return new BoneCPDataSource(driverClass: project.database.driverName, jdbcUrl: project.database.url, username: project.database.user, password: project.database.password);
        }
        user = env.getProperty('database.url')
        return new BoneCPDataSource(
                driverClass: env.getProperty('database.driverName'),
                jdbcUrl: env.getProperty('database.url'),
                username: env.getProperty('database.user'),
                password: env.getProperty('database.password')
        )
    }

    public SqlSession createSession() {
        def dataSource = getDatasource()
        org.apache.ibatis.mapping.Environment environment = new org.apache.ibatis.mapping.Environment(DEFAULT_ENVIRONMENT, new JdbcTransactionFactory(), dataSource)
        Configuration configuration = new Configuration(environment)
        configuration.addMapper(TableMapper.class)
        configuration.addMapper(PostgresTableMapper)
        logger.info("Opening Connection")
        return (new SqlSessionFactoryBuilder().build(configuration)).openSession()
    }
}
