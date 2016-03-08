package com.javalad.habitdeveloper.configuration;

import com.javalad.habitdeveloper.util.DataSourceProperties;
import liquibase.integration.spring.SpringLiquibase;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author KotovDV
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.javalad.habitdeveloper.dao.mapper")
@EnableConfigurationProperties(DataSourceProperties.class)
@ComponentScan(basePackages = "com.javalad.habitdeveloper.dao")
public class DataSourceConfiguration  {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setInitialSize(10);
        return dataSource;
    }


    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        LiquibaseProperties liquibaseProperties = liquibaseProperties();
        liquibase.setChangeLog(liquibaseProperties.getChangeLog());
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setDataSource(dataSource());
        return liquibase;
    }

    @Bean
    public LiquibaseProperties liquibaseProperties() {
        LiquibaseProperties properties = new LiquibaseProperties();
        properties.setUrl(dataSourceProperties.getUrl());
        properties.setUser(dataSourceProperties.getUsername());
        properties.setPassword(dataSourceProperties.getPassword());
        properties.setChangeLog("classpath:database.xml");
        return properties;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
