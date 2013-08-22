<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:jpa="http://www.springframework.org/schema/data/jpa"
       xmlns:jee="http://www.springframework.org/schema/jee"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans-3.1.xsd
           http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-3.1.xsd
           http://www.springframework.org/schema/tx     http://www.springframework.org/schema/tx/spring-tx-3.1.xsd
           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.1.xsd
           http://www.springframework.org/schema/data/jpa http://www.springframework.org/schema/data/jpa/spring-jpa.xsd
           http://www.springframework.org/schema/jee http://www.springframework.org/schema/jee/spring-jee-3.0.xsd
           http://www.springframework.org/schema/aop   http://www.springframework.org/schema/aop/spring-aop-3.1.xsd">

    <tx:annotation-driven/>

    <jpa:repositories base-package="${project.group}.${project.name}.persistence" />

    <context:component-scan base-package="${project.group}.${project.name}.service"/>

    <!--<jee:jndi-lookup id="dataSource" expected-type="javax.sql.DataSource" jndi-name="${project.name}Ds"/>-->
    <!-- this will import development/test datasource while in development profile -->
    <import resource="datasource.xml" />

    <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="packagesToScan" value="${project.group}.${project.name}.domain" />
        <property name="jpaVendorAdapter">
            <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
                <property name="showSql" value="true"/>
                <property name="generateDdl" value="true"/>
                <property name="databasePlatform" value="org.hibernate.dialect.H2Dialect"/>
            </bean>
        </property>
    </bean>

    <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
        <property name="entityManagerFactory" ref="entityManagerFactory" />
    </bean>

    <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
        <property name="basenames">
            <list><value>${project.name}_messages</value></list>
        </property>
    </bean>

</beans>
