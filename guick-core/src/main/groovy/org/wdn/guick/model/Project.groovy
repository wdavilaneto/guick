package org.wdn.guick.model

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.wdn.guick.loader.Json

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
    List<Table> tables = new ArrayList<Table>();
    List<Entity> entities = new ArrayList<Entity>();
    List<Clazz> classes = new ArrayList<Clazz>();

    DatasourceInfo database
    def pom
    def gradleProject
    def metadata = [:]

    def config = [:];

    @Transient
    def initialize(String rootPath) {
        if (rootPath == null) {
            path = new File("").getCanonicalPath()
        } else {
            // is it Test... we need to add classpaths ...
            path = new File(rootPath).getCanonicalPath()
//            ClassPathManager.addURLToSystemClassLoader(new URL("file",null,"${this.path}/build/classes/main/"))
//            ClassPathManager.addURLToSystemClassLoader(new URL("file",null,"${this.path}/build/resources/main/"))
//            ClassPathManager.addURLToSystemClassLoader(new URL("file",null,"${this.path}/src/main/resources"))

//            this.class.classLoader.rootLoader.addURL( new URL("file",null,"${this.path}/build/classes/main/") );
//            this.class.classLoader.rootLoader.addURL( new URL("file",null,"${this.path}/build/resources/main/") );
//            this.class.classLoader.rootLoader.addURL( new URL("file",null,"${this.path}/src/main/resources") );

        }
//        def guickFilePath = path + "/pom.xml"
//        if (new File(guickFilePath).exists()) {
//            pom = new XmlSlurper(false, false).parse(guickFilePath);
//            group = pom.groupId
//            name = pom.artifactId
//        } else {
//            //
//        }
        File guickFile = new File(path)
        if (!guickFile.exists()) {
            guickFile.mkdirs();
        }
        guickFile = new File(path + "/guick.json")
        if (guickFile.exists()) {
            config = new JsonSlurper().parse(guickFile);
            group = config.group
            name = config.name
            logger.debug("initializing with ${config}")
        } else {
            if (new File(path + "/pom.xml").exists()) {
                pom = new XmlSlurper(false, false).parse(new File(path + "/pom.xml"));
                config.group = pom.groupId.toString();
                config.name = pom.artifactId.toString();
            } else {
                config.group = "org.wdn.configure";
                config.name = "configure-me";
            }
            config.guickConnectionInfo = new DatasourceInfo()
            config.generatedDatasourceInfo = new DatasourceInfo()
            config.generationLanguage = "java";

            // if no pom nither guick.json exists, create one and stop any generation
            def json = new JsonBuilder(config).toPrettyString();
            guickFile.write(json);

            logger.warn "******** GUICK MESSAGE: READ THIS ********* "
            logger.warn "NO file ${guickFile} found !!!"
            logger.warn "creating an example file";
            logger.warn "Make sure to configure it before running any generator task again";
            logger.warn "Or it will create all with default values";
            logger.warn "******** ************************ ********* "
            throw new FileNotFoundException("NO file ${guickFile} found");
        }

    }

    @Transient
    public void persist (){
        def json = new JsonBuilder(tables).toPrettyString();
        new File (path + "/tables.json").write(json);
//        json = new JsonBuilder(tables).toPrettyString();
//        new File (path + "/metadata.json").write(json);
//        json = new JsonBuilder(entities).toPrettyString();
//        new File (path + "/metadata.json").write(json);
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

    private boolean hasHibernateIssue(Entity entity) {
        if (entity.parent?.id instanceof Entity) {
            Entity parentId = (Entity) entity.parent.id
            return parentId.isEmbeddable();
        }
        return false;
    }

    def getData() {
        if (data == null) {
            data = [:]
        }
        return data
    }

    public String getPackageBase() {
        return group.replaceAll("\\.", '/') + "/" + getAcronym();
    }

    public String getAcronym() {
        return name.replaceAll('\\\\', "/").replaceAll("-", "/").split("/")?.last();
    }

}
