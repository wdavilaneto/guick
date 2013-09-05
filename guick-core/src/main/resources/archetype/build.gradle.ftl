buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        //maven { url uri('file:/home/walter/.m2/repository/') }
    }
    dependencies {
        classpath 'org.gradle.api.plugins:gradle-tomcat-plugin:0.9.9'
        classpath group: 'org.wdn.guick', name: 'guick-plugin', version: '1.1-SNAPSHOT'
    }
}

apply plugin: 'java'
apply plugin: 'groovy'
apply plugin: 'guick'
apply plugin: 'tomcat'
apply plugin: 'maven'

repositories {
    mavenLocal()
    mavenCentral()
}

def spring_version = "3.2.3.RELEASE"

dependencies {

    compile "jstl:jstl:1.2"
    compile "org.postgresql:postgresql:9.2-1003-jdbc4"
    compile "mysql:mysql-connector-java:5.1.25"
    compile 'com.h2database:h2:1.3.173'
    compile "org.thymeleaf:thymeleaf:2.0.18"
    compile "org.thymeleaf:thymeleaf-spring3:2.0.18"

    compile "org.springframework:spring-context:${spring_version}"
    compile "org.springframework:spring-webmvc:${spring_version}"

    compile("org.springframework.data:spring-data-jpa:1.3.4.RELEASE") {
        exclude group: 'org.springframework', module: 'spring-tx'
        exclude group: 'org.springframework:', module: 'spring-orm'
        exclude group: 'org.springframework:', module: 'spring-jdbc'
    }

    compile "org.springframework:spring-tx:${spring_version}"
    compile "org.springframework:spring-orm:${spring_version}"
    compile "org.springframework:spring-jdbc:${spring_version}"

    compile "org.hibernate:hibernate-entitymanager:4.2.4.Final"
    compile "org.hibernate:hibernate-validator:5.0.1.Final"
    compile "com.fasterxml.jackson.core:jackson-core:2.2.2"
    compile "com.fasterxml.jackson.core:jackson-databind:2.2.2"

    compile "org.codehaus.groovy:groovy-all:2.1.6"

    // logging ..
    compile 'org.slf4j:slf4j-api:1.6.1'
    compile 'ch.qos.logback:logback-core:0.9.24'
    compile 'ch.qos.logback:logback-classic:0.9.24'
    compile 'org.slf4j:jcl-over-slf4j:1.6.1'
    compile 'org.slf4j:log4j-over-slf4j:1.6.1'
    compile 'org.slf4j:jul-to-slf4j:1.6.1'

    def tomcatVersion = '7.0.11'
    tomcat "org.apache.tomcat.embed:tomcat-embed-core:${tomcatVersion}",
            "org.apache.tomcat.embed:tomcat-embed-logging-juli:${tomcatVersion}"
    tomcat("org.apache.tomcat.embed:tomcat-embed-jasper:${tomcatVersion}") {
        exclude group: 'org.eclipse.jdt.core.compiler', module: 'ecj'
    }
    guick 'org.wdn.guick:guick-core:1.1-SNAPSHOT'

    testCompile "org.springframework:spring-test:${spring_version}"
    testCompile "junit:junit:4.11"
}

configurations {
    guick
}