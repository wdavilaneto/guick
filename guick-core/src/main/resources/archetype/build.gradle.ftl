buildscript {
    repositories {
        jcenter()
        mavenLocal()
    }
    dependencies {
        classpath 'org.gradle.api.plugins:gradle-tomcat-plugin:1.2.4'
    }##classpath group: 'org.wdn.guick', name: 'guick-plugin', version: '1.1-SNAPSHOT'

}

apply plugin: 'groovy'
apply plugin: 'tomcat'
##apply plugin: 'guick'
apply plugin: 'maven'

repositories {
    mavenLocal()
    maven {
        credentials {
            username = 'deployment'
            password = '3cl1ps3'
        }
        url 'http://p-intcont01.pgj.rj.gov.br/nexus/content/groups/public'
    }
    mavenCentral()
}

group = 'org.wdn.example'
version = '1.0.0-SNAPSHOT'

targetCompatibility = '1.7'
sourceCompatibility = '1.7'

tomcatRun {
    httpPort = 8080
    additionalRuntimeJars = [file("src/test/resources")] // for External Configuration path
}

dependencies {

    final SPRING_VERSION = '4.0.0.RELEASE'
    final THYMELEAF_VERSION = '2.1.2.RELEASE'
    final BATCH_ADMIN_VERSION = '1.2.2.RELEASE'

    compile "org.codehaus.groovy:groovy-all:2.2.1"

    // Web layer
    runtime "jstl:jstl:1.2"
    runtime "org.thymeleaf:thymeleaf:${THYMELEAF_VERSION}"
    runtime "org.thymeleaf:thymeleaf-spring4:${THYMELEAF_VERSION}"
    runtime "nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:1.2.2"

    compile "org.springframework:spring-webmvc:${SPRING_VERSION}"
    compile "org.springframework.batch:spring-batch-admin-resources:${BATCH_ADMIN_VERSION}"
    compile "commons-lang:commons-lang:2.6"

    // spring data
    compile("org.springframework.data:spring-data-jpa:1.3.4.RELEASE")

    compile "org.hibernate:hibernate-entitymanager:4.2.4.Final"
    compile "org.hibernate:hibernate-validator:5.0.1.Final"

    // Jackson/Json
    compile "com.fasterxml.jackson.core:jackson-core:2.2.2"
    compile "com.fasterxml.jackson.core:jackson-databind:2.2.2"

    runtime "commons-dbcp:commons-dbcp:1.4"

    final SPOCK_VERSION = '0.7-groovy-2.0'
    final LOGBACK_VERSION = '1.0.13'
    final SLF4J_VERSION = '1.7.5'

    // logging ..
    runtime group: 'ch.qos.logback', name: 'logback-core', version: LOGBACK_VERSION
    runtime group: 'ch.qos.logback', name: 'logback-classic', version: LOGBACK_VERSION
    runtime group: 'org.slf4j', name: 'slf4j-api', version: SLF4J_VERSION
    runtime group: 'org.slf4j', name: 'jcl-over-slf4j', version: SLF4J_VERSION
    runtime group: 'org.slf4j', name: 'jul-to-slf4j', version: SLF4J_VERSION

    // test ...
    testCompile "junit:junit:4.11"
    testCompile "org.springframework:spring-test:${SPRING_VERSION}"
    testCompile group: 'org.spockframework', name: 'spock-core', version: SPOCK_VERSION
    testCompile group: 'org.spockframework', name: 'spock-spring', version: SPOCK_VERSION
    testCompile group: 'org.springframework', name: 'spring-test', version: SPRING_VERSION
    testCompile group: 'org.thymeleaf', name: 'thymeleaf-testing', version: THYMELEAF_VERSION

    runtime group: 'com.h2database', name: 'h2', version: '1.3.174'
    //runtime 'com.oracle:ojdbc6:11.2.0.3'

    final tomcatVersion = '7.0.11'
    tomcat "org.apache.tomcat.embed:tomcat-embed-core:${tomcatVersion}",
            "org.apache.tomcat.embed:tomcat-embed-logging-juli:${tomcatVersion}"
    tomcat("org.apache.tomcat.embed:tomcat-embed-jasper:${tomcatVersion}") {
        exclude group: 'org.eclipse.jdt.core.compiler', module: 'ecj'
    }

}

task wrapper(type: Wrapper) {
    gradleVersion = '1.11'
}