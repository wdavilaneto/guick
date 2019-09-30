package org.wdn.guick.model

import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
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
    def gradleProject
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
            if (new File(path + "/pom.xml").exists()) {
                pom = new XmlSlurper(false, false).parse(new File(path + "/pom.xml"));
                config.group = pom.groupId.toString();
                config.name = pom.artifactId.toString();
            } else {
                config.group = "org.wdn.configure";
                config.name = "configureme";
            }
            config.guickConnectionInfo = new DatasourceInfo()
            config.generatedDatasourceInfo = new DatasourceInfo()
            config.generationLanguage = "java";
            config.tablePrefix = "TB";
            config.useWorkflow ="false";
            config.tables = [[owner:"schema", tableName:"example_table"]];
            config.description = "Project Description";

            // if no pom nither guick.json exists, create one and stop any generation
             def json = new JsonBuilder(config).toPrettyString();
//            def json = JsonOutput.toJson(config)
            guickFile.write(json);

            logger.warn "******** GUICK MESSAGE: READ THIS ********* "
            logger.warn "NO file ${guickFile} found !!!"
            logger.warn "creating an example file";
            logger.warn "Make sure to configure it before running any generator task again";
            logger.warn "Or it will create all with default values";
            logger.warn "******** ************************ ********* "
            System.exit(0)
        }
    }

    @Transient
    public void persist() {
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

    public String getName() {
        if (name == null || name.isEmpty()) {
            name = path.replaceAll('\\\\', "/").replaceAll("-", "/").split("/")?.last();
        }
        return name;
    }

    public String getGroup() {
        if (this.group != null) {
            return this.group
        }
        return getName()
    }

    public String getLastGroup() {
        if (this.group != null) {
            return this.group.split("\\.")?.last();
        }
        return getName()
    }


    public String getSourcePath() {
        return EngineConstants.DEFAULT_JAVA_SRC_WITH_PACKAGE + "/" + getAcronym()
    }

    public String getTestSourcePath() {
        return EngineConstants.DEFAULT_JAVA_TEST_WITH_PACKAGE + "/" + getAcronym()
    }

    public List<EnumClass> getEnums() {
        def enumList = []
        return (List<EnumClass>) entities.each { entity -> enumList.addAll(entity.enums) }
    }

    public List<Entity> getEntitiesWithoutHibernateIssue() {
        return entities.findAll { e -> !hasHibernateIssue(e) }
    }

    public List<Entity> getAllMainEntities() {
        return getEntitiesWithoutHibernateIssue().findAll() { it.looksLikeMainEntity() };
    }
    public List<Entity> getAllDomainEntities() {
        return getEntitiesWithoutHibernateIssue().findAll() { it.looksLikeDomain() };
    }
    public List<Entity> getAllEnumLikeEntities() {
        return getEntitiesWithoutHibernateIssue().findAll() {  it.looksLikeEnum()};
    }

    public List<Entity> getAllEntitiesWithDeadline(){
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

    public String getPackageBase() {
        return group.replaceAll("\\.", '/') + "/" + getAcronym();
    }

    public String getAcronym() {
        return name.replaceAll('\\\\', "/").replaceAll("-", "/").split("/")?.last();
    }

    public String getBeanName() {
        return stringUtil.uncapitalize(name);
    }

}
