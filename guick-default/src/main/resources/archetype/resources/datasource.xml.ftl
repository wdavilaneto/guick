<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans-3.1.xsd">


    <!--<bean id="dataSource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">-->
    <!--<property name="driverClass" value="org.h2.Driver"/>-->
    <!--<property name="url" value="jdbc:h2:mem:${project.name}Ds;MODE=Oracle;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"/>-->
    <!--<property name="username" value="sa"/>-->
    <!--<property name="password" value=""/>-->
    <!--</bean>-->

    <bean id="dataSource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
        <property name="driverClass" value="org.sqlite.JDBC"/>
        <property name="url" value="jdbc:sqlite:${project.name}.db"/>
    </bean>

</beans>