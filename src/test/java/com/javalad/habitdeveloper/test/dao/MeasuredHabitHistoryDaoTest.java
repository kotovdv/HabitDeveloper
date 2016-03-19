package com.javalad.habitdeveloper.test.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.javalad.habitdeveloper.dao.MeasuredHabitHistoryDao;
import com.javalad.habitdeveloper.domain.MeasuredHabitHistory;
import com.javalad.habitdeveloper.test.dao.util.AbstractDaoTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author KotovDV
 */
public class MeasuredHabitHistoryDaoTest extends AbstractDaoTest {

    @Resource
    private MeasuredHabitHistoryDao measuredHabitHistoryDao;

    @Test
    @ExpectedDatabase(value = "classpath:dao/MeasuredHabitHistoryDaoTest/addTest/after.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void addTest() {
        MeasuredHabitHistory history = new MeasuredHabitHistory(1, getTestingDate(2016, 1, 1, 21, 0, 0, 0), 100.0);
        measuredHabitHistoryDao.add(history);
        assertEquals(history.getId(), 1L);
    }

    @Test
    @DatabaseSetup("classpath:dao/MeasuredHabitHistoryDaoTest/getTest/before.xml")
    public void getTest() {
        MeasuredHabitHistory history = measuredHabitHistoryDao.get(1L);
        assertEquals(history.getId(), 1L);
        assertEquals(history.getMeasuredHabitId(), 1L);
        assertEquals(history.getCheckDate(), getTestingDate(2016, 1, 1, 21, 0, 0, 0));
        assertEquals(history.getMeasuredValue(), 100, 0);
    }

    @Test
    @DatabaseSetup("classpath:dao/MeasuredHabitHistoryDaoTest/updateTest/before.xml")
    @ExpectedDatabase(value = "classpath:dao/MeasuredHabitHistoryDaoTest/updateTest/after.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void updateTest() {
        MeasuredHabitHistory history = new MeasuredHabitHistory(2, getTestingDate(2016, 1, 1, 22, 0, 0, 0), 200.0);
        history.setId(1);
        measuredHabitHistoryDao.update(history);
    }

    @Test
    @DatabaseSetup("classpath:dao/MeasuredHabitHistoryDaoTest/deleteTest/before.xml")
    @ExpectedDatabase(value = "classpath:dao/MeasuredHabitHistoryDaoTest/deleteTest/after.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteTest() {
        measuredHabitHistoryDao.delete(2L);
    }

    @Test
    @DatabaseSetup("classpath:dao/MeasuredHabitHistoryDaoTest/countTest/before.xml")
    public void countTest() {
        assertEquals(measuredHabitHistoryDao.count(), 3);
    }

    @Test
    @DatabaseSetup("classpath:dao/MeasuredHabitHistoryDaoTest/getAllTest/before.xml")
    public void getAllTest() {
        List<MeasuredHabitHistory> histories = measuredHabitHistoryDao.getAll();
        assertEquals(histories.size(), 3);
        histories.forEach(history -> {
            long historyId = history.getId();
            assertTrue(historyId >= 1 && historyId <= 3);
            assertEquals(history.getMeasuredHabitId(), historyId);
            assertEquals(history.getCheckDate(), getTestingDate(2016, 1, 1, 21, 0, 0, 0));
            assertEquals(history.getMeasuredValue(), historyId * 100.0, 0);
        });
    }

    @Test
    @DatabaseSetup("classpath:dao/MeasuredHabitHistoryDaoTest/existsTest/before.xml")
    public void existsTest() {
        assertTrue(measuredHabitHistoryDao.exists(1L));
        assertFalse(measuredHabitHistoryDao.exists(25L));
    }

    @Test
    @DatabaseSetup("classpath:dao/MeasuredHabitHistoryDaoTest/getByHabitIdTest/before.xml")
    public void getByHabitIdTest() {
        List<MeasuredHabitHistory> histories = measuredHabitHistoryDao.getByHabitId(1);
        assertEquals(histories.size(), 3);
        histories.forEach(history -> {
            long historyId = history.getId();
            assertTrue(historyId >= 1 && historyId <= 3);
            assertEquals(history.getMeasuredHabitId(), 1);
            assertEquals(history.getCheckDate(), getTestingDate(2016, 1, 1, 21, 0, 0, 0));
            assertEquals(history.getMeasuredValue(), historyId * 100.0, 0);
        });
    }


}
