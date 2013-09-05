buildscript {
    repositories {
        mavenCentral()
        mavenLocal()
    }
    dependencies {
        classpath 'org.gradle.api.plugins:gradle-tomcat-plugin:0.9.9'
        classpath group: 'org.wdn.guick', name: 'guick-plugin', version: '1.1-SNAPSHOT'
    }
}

apply plugin: 'java'
apply plugin: 'groovy'
apply plugin: 'tomcat'
apply plugin: 'maven'
apply plugin: 'guick'

repositories {
    mavenCentral()
    mavenLocal()
}

def spring_version = "3.1.4.RELEASE"

tomcatRun {
    contextPath = "/"
}

dependencies {

    compile "org.codehaus.groovy:groovy-all:1.8.6"

    runtime "jstl:jstl:1.2"
    runtime 'com.h2database:h2:1.3.173'
    runtime "org.thymeleaf:thymeleaf:2.0.18"
    runtime "org.thymeleaf:thymeleaf-spring3:2.0.18"

    compile "org.springframework:spring-context:${spring_version}"
    compile "org.springframework:spring-webmvc:${spring_version}"

    compile("org.springframework.data:spring-data-jpa:1.3.4.RELEASE")

    compile "org.hibernate:hibernate-entitymanager:4.2.4.Final"
    compile "org.hibernate:hibernate-validator:5.0.1.Final"
    compile "com.fasterxml.jackson.core:jackson-core:2.2.2"
    compile "com.fasterxml.jackson.core:jackson-databind:2.2.2"

    // logging ..
    compile 'org.slf4j:slf4j-api:1.6.1'
    runtime 'ch.qos.logback:logback-core:1.0.13'
    runtime 'ch.qos.logback:logback-classic:1.0.13'
    runtime 'org.slf4j:jcl-over-slf4j:1.6.1'
    runtime 'org.slf4j:log4j-over-slf4j:1.6.1'
    runtime 'org.slf4j:jul-to-slf4j:1.6.1'

    def tomcatVersion = '7.0.11'
    tomcat "org.apache.tomcat.embed:tomcat-embed-core:${tomcatVersion}",
            "org.apache.tomcat.embed:tomcat-embed-logging-juli:${tomcatVersion}"
    tomcat("org.apache.tomcat.embed:tomcat-embed-jasper:${tomcatVersion}") {
        exclude group: 'org.eclipse.jdt.core.compiler', module: 'ecj'
    }

    testCompile "org.springframework:spring-test:${spring_version}"
    testCompile "junit:junit:4.11"
}
