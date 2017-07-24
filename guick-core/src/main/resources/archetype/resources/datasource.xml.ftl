<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans-3.1.xsd">


##    <bean id="dataSource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
##        <property name="driverClass" value="org.h2.Driver"/>
##        <property name="url" value="jdbc:h2:mem:wdavilanetoDs;MODE=Oracle"/>
##        <property name="username" value="sa"/>
##        <property name="password" value=""/>
##    </bean>

    <!-- FIXME: change to jndi datasource -->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
        <property name="driverClass" value="oracle.jdbc.driver.OracleDriver"/>
        <property name="url" value="jdbc:oracle:thin:@d-dbora11g01:1521:dbmp"/>
        <property name="username" value="custeio"/>
        <property name="password" value="custeio"/>
    </bean>

    <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="packagesToScan" value="${project.group}.${project.name}.domain" />
        <property name="jpaVendorAdapter">
            <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
                <property name="showSql" value="true"/>
                <property name="databasePlatform" value="org.hibernate.dialect.Oracle10gDialect"/>
            </bean>
        </property>
    </bean>

    <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
        <property name="entityManagerFactory" ref="entityManagerFactory" />
    </bean>

</beans>