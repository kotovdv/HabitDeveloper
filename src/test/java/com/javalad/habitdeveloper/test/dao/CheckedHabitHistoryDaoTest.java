package com.javalad.habitdeveloper.test.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.javalad.habitdeveloper.dao.CheckedHabitHistoryDao;
import com.javalad.habitdeveloper.domain.CheckedHabitHistory;
import com.javalad.habitdeveloper.test.dao.util.AbstractDaoTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author KotovDV
 */
public class CheckedHabitHistoryDaoTest extends AbstractDaoTest {

    @Resource
    private CheckedHabitHistoryDao checkedHabitHistoryDao;

    @Test
    @ExpectedDatabase(value = "classpath:dao/CheckedHabitHistoryDaoTest/addTest/after.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void addTest() {
        CheckedHabitHistory history = new CheckedHabitHistory(1, getTestingDate(2016, 1, 1, 21, 0, 0, 0), false);
        checkedHabitHistoryDao.add(history);
        assertEquals(history.getId(), 1L);
    }

    @Test
    @DatabaseSetup(value = "classpath:dao/CheckedHabitHistoryDaoTest/getTest/before.xml")
    public void getTest() {
        CheckedHabitHistory habitHistory = checkedHabitHistoryDao.get(1L);
        assertEquals(habitHistory.getId(), 1L);
        assertEquals(habitHistory.getCheckedHabitId(), 1L);
        assertEquals(habitHistory.getCheckDate(), getTestingDate(2016, 1, 1, 21, 0, 0, 0));
        assertEquals(habitHistory.getCheckFlag(), false);
    }

    @Test
    @DatabaseSetup(value = "classpath:dao/CheckedHabitHistoryDaoTest/updateTest/before.xml")
    @ExpectedDatabase(value = "classpath:dao/CheckedHabitHistoryDaoTest/updateTest/after.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void updateTest() {
        CheckedHabitHistory habitHistory = new CheckedHabitHistory(2, getTestingDate(2016, 1, 1, 22, 0, 0, 0), true);
        habitHistory.setId(1L);
        checkedHabitHistoryDao.update(habitHistory);
    }

    @Test
    @DatabaseSetup(value = "classpath:dao/CheckedHabitHistoryDaoTest/deleteTest/before.xml")
    @ExpectedDatabase(value = "classpath:dao/CheckedHabitHistoryDaoTest/deleteTest/after.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteTest() {
        checkedHabitHistoryDao.delete(2L);
    }

    @Test
    @DatabaseSetup(value = "classpath:dao/CheckedHabitHistoryDaoTest/countTest/before.xml")
    public void countTest() {
        assertEquals(checkedHabitHistoryDao.count(), 3);
    }

    @Test
    @DatabaseSetup(value = "classpath:dao/CheckedHabitHistoryDaoTest/getAllTest/before.xml")
    public void getAllTest() {
        List<CheckedHabitHistory> histories = checkedHabitHistoryDao.getAll();
        assertEquals(histories.size(), 3);
        histories.forEach(history -> {
            long historyId = history.getId();
            assertTrue(historyId >= 1 && historyId <= 3);
            assertEquals(history.getCheckedHabitId(), historyId);
            assertEquals(history.getCheckDate(), getTestingDate(2016, 1, 1, 21, 0, 0, 0));
            assertEquals(history.getCheckFlag(), historyId % 2 == 0 ? false : true);
        });
    }

    @Test
    @DatabaseSetup(value = "classpath:dao/CheckedHabitHistoryDaoTest/existsTest/before.xml")
    public void existsTest() {
        assertTrue(checkedHabitHistoryDao.exists(1L));
        assertFalse(checkedHabitHistoryDao.exists(2L));
    }

    @Test
    @DatabaseSetup(value = "classpath:dao/CheckedHabitHistoryDaoTest/getByHabitIdTest/before.xml")
    public void getByHabitIdTest() {
        List<CheckedHabitHistory> histories = checkedHabitHistoryDao.getByHabitId(1L);
        assertEquals(histories.size(), 3);
        histories.forEach(history -> {
            long historyId = history.getId();
            assertTrue(historyId >= 1 && historyId <= 3);
            assertEquals(history.getCheckedHabitId(), 1);
            assertEquals(history.getCheckDate(), getTestingDate(2016, 1, 1, 21, 0, 0, 0));
            assertEquals(history.getCheckFlag(), historyId % 2 == 0 ? false : true);
        });

    }


}
