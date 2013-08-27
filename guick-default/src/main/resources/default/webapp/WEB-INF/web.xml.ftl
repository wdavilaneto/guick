<?xml version="1.0" encoding="UTF-8"?>

<web-app version="3.0"
         xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         metadata-complete="false">

    <display-name>${project.name}</display-name>

    <distributable/>

    <!-- Execucao no weblogic -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:/application.xml</param-value>
    </context-param>

    <listener>
        <description>Listener de contexto do Spring. Apenas remover se o Spring não for usado.</description>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <listener>
        <description>Listener de request do Spring. Apenas remover se o Spring não for usado.</description>
        <listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
    </listener>

    <servlet>
        <servlet-name>SpringMVCServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/spring-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>SpringMVCServlet</servlet-name>
        <url-pattern>*.do</url-pattern>
    </servlet-mapping>

    <jsp-config>
        <jsp-property-group>
            <url-pattern>*.jsp</url-pattern>
            <url-pattern>*.tag</url-pattern>
            <page-encoding>UTF-8</page-encoding>
            <trim-directive-whitespaces>true</trim-directive-whitespaces>
        </jsp-property-group>
    </jsp-config>

    <error-page>
        <error-code>404</error-code>
        <location>/404.html</location>
    </error-page>

    <error-page>
        <error-code>500</error-code>
        <location>/error.jsp</location>
    </error-page>

    <welcome-file-list>
        <welcome-file>/index.html</welcome-file>
        <welcome-file>/home.do</welcome-file>
    </welcome-file-list>
         
</web-app>