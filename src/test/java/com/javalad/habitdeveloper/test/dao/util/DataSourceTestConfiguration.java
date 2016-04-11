package com.javalad.habitdeveloper.test.dao.util;

import com.javalad.habitdeveloper.configuration.DataSourceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author KotovDV
 */
@Configuration
@Import({DataSourceConfiguration.class})
@ComponentScan(value = "com.javalad.habitdeveloper.dao")
public class DataSourceTestConfiguration {}