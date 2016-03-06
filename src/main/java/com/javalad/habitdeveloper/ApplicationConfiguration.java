package com.javalad.habitdeveloper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * @author KotovDV
 */

@EnableWebMvc
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "com.javalad.habitdeveloper")
public class ApplicationConfiguration {

    private final Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }

    @Bean
    public DataSource hsqlDataSourcePool() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:file:database/hsqlDataSource;hsqldb.tx=mvcc;hsqldb.write_delay=false;shutdown=true");
        dataSource.setInitialSize(10);
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        boolean tableExists = jdbcTemplate.queryForObject("SELECT COUNT(1)" +
                " FROM INFORMATION_SCHEMA.TABLES " +
                "WHERE TABLE_NAME= 'INITIALIZATION'", Boolean.class);
        if(tableExists){
            logger.info("Using existing database");
        }else{
            logger.info("Creating new database from init_schema.sql");
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator(new ClassPathResource("init_schema.sql"));
            DatabasePopulatorUtils.execute(populator, dataSource);
        }
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(hsqlDataSourcePool());
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(hsqlDataSourcePool());
    }


}
