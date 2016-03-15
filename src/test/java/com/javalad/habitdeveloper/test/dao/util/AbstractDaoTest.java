package com.javalad.habitdeveloper.test.dao.util;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.javalad.habitdeveloper.configuration.DataSourceConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author KotovDV
 */
@Rollback
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DataSourceConfiguration.class)
@TestExecutionListeners(value = {
        DisableConstraintsListener.class,
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
public abstract class AbstractDaoTest {



   /* public abstract void addTest();

    public abstract void getTest();

    public abstract void updateTest();

    public abstract void deleteTest();

    public abstract void countTest();

    public abstract void getAllTest();*/

}
