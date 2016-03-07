package com.javalad.habitdeveloper.test.dao;

import com.javalad.habitdeveloper.configuration.DataSourceConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author KotovDV
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DataSourceConfiguration.class)
public abstract class AbstractDaoTest {


}
