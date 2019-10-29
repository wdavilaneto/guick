package org.wdn.guick.model

import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.wdn.guick.core.VelocityTemplateWriter
import org.wdn.guick.util.StringUtil

import javax.persistence.Transient

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/13/13
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
class Project implements Serializable {

    @Autowired
    VelocityTemplateWriter templateWriter

    private final Logger logger = LoggerFactory.getLogger(this.class)

    String group
    String name
    String path
    String description
    List<Table> tables = new ArrayList<Table>();
    List<Entity> entities = new ArrayList<Entity>();
    List<Clazz> classes = new ArrayList<Clazz>();

    private StringUtil stringUtil = new StringUtil();

    DatasourceInfo database
    def pom
    def metadata = [:]
    def targetTables = [];

    def config = [:];

    @Transient
    def initialize(String rootPath) {
        if (rootPath == null) {
            path = new File("").getCanonicalPath()
        } else {
            // is it Test... we need to add classpaths ...
            path = new File(rootPath).getCanonicalPath()
        }
        File guickFile = new File(path)
        if (!guickFile.exists()) {
            guickFile.mkdirs();
        }
        guickFile = new File(path + "/guick.json")
        if (guickFile.exists()) {
            config = configure(guickFile);
        } else {
            createConfigure(guickFile);
        }
    }

    @Transient
    def void persist() {
        def json = new JsonBuilder(tables).toPrettyString();
        new File(path + "/tables.json").write(json);
    }

    @Transient
    def configure(File guickFile) {
        logger.debug("initializing with ${config}")
        config = new JsonSlurper().parse(guickFile);
        group = config.group
        name = config.name
        this.database = config.guickConnectionInfo;
        this.targetTables = config.tables;
        this.description = config.description;
        return config;
    }

    @Transient
    def createConfigure(File guickFile) {
        createConfigOrGetFromPom()
        templateWriter.execute("init/guick.json", "", config)
        templateWriter.execute("init/example_ddl.sql", "", config)
        templateWriter.execute("init/README.md", "", config)

        logger.warn "******** GUICK MESSAGE: READ THIS ********* "
        logger.warn "NO file ${guickFile} found !!! creating an example file"
        logger.warn "Make sure to configure it before running any generator task again!";
        logger.warn "README.md generated";
        logger.warn "******** ************************ ********* "
        System.exit(0)
    }

    String getName() {
        if (name == null || name.isEmpty()) {
            name = path.replaceAll('\\\\', "/").replaceAll("-", "/").split("/")?.last();
        }
        return name;
    }

    String getGroup() {
        if (this.group != null) {
            return this.group
        }
        return getName()
    }

    String getLastGroup() {
        if (this.group != null) {
            return this.group.split("\\.")?.last();
        }
        return getName()
    }

    private createConfigOrGetFromPom() {
        if (new File(path + "/pom.xml").exists()) {
            pom = new XmlSlurper(false, false).parse(new File(path + "/pom.xml"));
            config.group = pom.groupId.toString();
            config.name = pom.artifactId.toString();
        } else {
            config.group = "org.wdn.configure";
            config.name = "configureme";
        }
        return config
    }

    def String getSourcePath() {
        return EngineConstants.DEFAULT_JAVA_SRC_WITH_PACKAGE + "/" + getAcronym()
    }

    def String getTestSourcePath() {
        return EngineConstants.DEFAULT_JAVA_TEST_WITH_PACKAGE + "/" + getAcronym()
    }

    def List<EnumClass> getEnums() {
        def enumList = []
        return (List<EnumClass>) entities.each { entity -> enumList.addAll(entity.enums) }
    }

    def List<Entity> getEntitiesWithoutHibernateIssue() {
        return entities.findAll { e -> !hasHibernateIssue(e) }
    }

    def List<Entity> getAllMainEntities() {
        return getEntitiesWithoutHibernateIssue().findAll() { it.looksLikeMainEntity() };
    }

    def List<Entity> getAllDomainEntities() {
        return getEntitiesWithoutHibernateIssue().findAll() { it.looksLikeDomain() };
    }

    def List<Entity> getAllEnumLikeEntities() {
        return getEntitiesWithoutHibernateIssue().findAll() { it.looksLikeEnum() };
    }

    def List<Entity> getAllEntitiesWithDeadline() {
        return getEntitiesWithoutHibernateIssue().findAll() { (it.getAllDeadlineProperties().size() > 0) };
    }

    private boolean hasHibernateIssue(Entity entity) {
//        if (entity.parent?.id instanceof Entity) {
//            Entity parentId = (Entity) entity.parent.id
//            return parentId.isEmbeddable();
//        }
//        return false;
        return false;
    }

    def getData() {
        if (this.data == null) {
            this.data = [:]
        }
        return this.data
    }

    def String getPackageBase() {
        return group.replaceAll("\\.", '/') + "/" + getAcronym();
    }

    def String getAcronym() {
        return name.replaceAll('\\\\', "/").replaceAll("-", "/").split("/")?.last();
    }

    def String getBeanName() {
        return stringUtil.uncapitalize(name);
    }

}
