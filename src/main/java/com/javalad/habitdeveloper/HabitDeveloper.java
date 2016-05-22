package com.javalad.habitdeveloper;

import com.javalad.habitdeveloper.configuration.DataSourceConfiguration;
import com.javalad.habitdeveloper.configuration.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author KotovDV
 */
@Import({DataSourceConfiguration.class, WebConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.javalad.habitdeveloper")
@ComponentScan(basePackages = "com.javalad.habitdeveloper")
public class HabitDeveloper {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HabitDeveloper.class, args);
    }

}
