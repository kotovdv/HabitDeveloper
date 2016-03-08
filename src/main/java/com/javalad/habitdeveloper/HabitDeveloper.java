package com.javalad.habitdeveloper;

import com.javalad.habitdeveloper.configuration.DataSourceConfiguration;
import com.javalad.habitdeveloper.dao.ProfileDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;

/**
 * @author KotovDV
 */
@EnableWebMvc
@Import(DataSourceConfiguration.class)
@SpringBootApplication(scanBasePackages = "com.javalad.habitdeveloper")
public class HabitDeveloper {

    @Resource
    ProfileDao profileDao;


    public static void main(String[] args) throws Exception {
        SpringApplication.run(HabitDeveloper.class, args);
    }


}
