package com.javalad.habitdeveloper.test.dao.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * @author KotovDV
 */
public class DisableConstraintsListener extends AbstractTestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        JdbcTemplate jdbcTemplate = testContext.getApplicationContext().getBean(JdbcTemplate.class);
        disableForeignKeys(jdbcTemplate);
        super.beforeTestClass(testContext);
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        JdbcTemplate jdbcTemplate = testContext.getApplicationContext().getBean(JdbcTemplate.class);
        enableForeignKeys(jdbcTemplate);
        super.afterTestClass(testContext);
    }

    protected void disableForeignKeys(JdbcTemplate jdbcTemplate) {
        changeForeignKeysState(jdbcTemplate, false);
    }

    protected void enableForeignKeys(JdbcTemplate jdbcTemplate) {
        changeForeignKeysState(jdbcTemplate, true);
    }

    private void changeForeignKeysState(JdbcTemplate jdbcTemplate, boolean enabled) {
        jdbcTemplate.execute("SET DATABASE REFERENTIAL INTEGRITY " + (enabled ? "TRUE" : "FALSE"));
    }


}
