apply plugin: 'guick'
apply plugin: 'java'
apply plugin: 'groovy'
apply plugin: 'war'
apply plugin: 'tomcat'
apply plugin: 'maven'


repositories{
    mavenLocal()
    mavenCentral()
    maven {
        url "http://repo.springsource.org/libs-milestone"
    }
}

def spring_version = "3.2.3.RELEASE"

dependencies {


    compile "jstl:jstl:1.2"
    compile "org.postgresqlpostgresql:9.2-1003-jdbc4"
    compile "mysql:mysql-connector-java:5.1.25"
    compile "org.springframework:spring-context:${"$"}{spring_version}"
    compile "org.springframework:spring-tx:${"$"}{spring_version}"
    compile "org.springframework:spring-webmvc:${"$"}{spring_version}"
    compile "org.springframework.data:spring-data-jpa:1.3.4.RELEASE"
    compile "org.hibernate:hibernate-entitymanager:4.2.4.Final"
    compile "org.hibernate:hibernate-validator:5.0.1.Final"
    compile "com.fasterxml.jackson.core:jackson-core:2.2.2"
    compile "com.fasterxml.jackson.core:jackson-databind:2.2.2"

    compile "org.codehaus.groovy:groovy-all:2.1.6"

    runtime "com.h2database:h2:1.3.173"

    def tomcatVersion = '7.0.11'
    tomcat "org.apache.tomcat.embed:tomcat-embed-core:${"$"}{tomcatVersion}",
           "org.apache.tomcat.embed:tomcat-embed-logging-juli:${"$"}{tomcatVersion}"
    tomcat("org.apache.tomcat.embed:tomcat-embed-jasper:${"$"}{tomcatVersion}") {
        exclude group: 'org.eclipse.jdt.core.compiler', module: 'ecj'
    }

    testCompile "junit:junit:4.11"
}


buildscript {

    repositories {
        mavenLocal()
        mavenCentral()
        maven {
            url uri('file:/home/walter/.m2/repository/')
        }
    }

    dependencies {
        classpath 'org.gradle.api.plugins:gradle-tomcat-plugin:0.9.9'
        classpath group: 'org.wdn.guick', name: 'guick-plugin', version: '1.0-SNAPSHOT'
        classpath group: 'org.wdn.guick', name: 'guick-default', version: '1.0-SNAPSHOT'
    }

}