/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import br.mp.mprj.commons.aop.OnSuccessAdvice;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.ehcache.EhCacheCacheManager;
//import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.Filter;
import javax.sql.DataSource;

@ComponentScan(basePackages = "br.mp.mprj.sev.api")
@Configuration
@EnableWebMvc
@MapperScan(basePackages = "br.mp.mprj.sev.api.persistence.mybatis")
@EnableJpaRepositories(basePackages = "br.mp.mprj.sev.api.persistence")
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class, WebSocketAutoConfiguration.class, JmxAutoConfiguration.class})
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableTransactionManagement
@EnableAspectJAutoProxy
//@EnableCaching
public class Application extends SpringBootServletInitializer {

//    private static final String EHCACHE_FILE = "ehcache.xml";

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public OnSuccessAdvice httpOkResponseAdvice() {
        return new OnSuccessAdvice();
    }

//   @Bean
//   public EhCacheManagerFactoryBean cacheFactoryBean() {
//       EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
//       ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource(EHCACHE_FILE));
//       ehCacheManagerFactoryBean.setShared(false);
//       ehCacheManagerFactoryBean.setCacheManagerName("sev_cache");
//       return ehCacheManagerFactoryBean;
//   }

//   @Bean
//   public CacheManager cacheManager(EhCacheManagerFactoryBean ehCacheManagerFactoryBean) {
//       EhCacheCacheManager cacheManager = new EhCacheCacheManager();
//       cacheManager.setCacheManager(ehCacheManagerFactoryBean.getObject());
//       return cacheManager;
//   }

    @Bean(name = "entityManager")
    public SharedEntityManagerBean entityManager() {
        SharedEntityManagerBean sharedEntityManagerBean = new SharedEntityManagerBean();
        return sharedEntityManagerBean;
    }

    @Bean
    @Profile("jboss")
    public DataSource dataSource() {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        return dataSourceLookup.getDataSource("java:jboss/sevDS");
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-settings.xml"));
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }

    @Bean
    public FilterRegistrationBean characterEncodingFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(characterEncodingFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("encoding", "UTF-8");
        registration.addInitParameter("forceEncoding", "true");
        registration.setName("characterEncodingFilter");
        return registration;
    }

    @Bean(name = "characterEncodingFilter")
    public Filter characterEncodingFilter() {
        return new org.springframework.web.filter.CharacterEncodingFilter();
    }
}