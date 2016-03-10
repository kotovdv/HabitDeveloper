package com.javalad.habitdeveloper.test.dao;

import com.javalad.habitdeveloper.dao.CheckedHabitHistoryDao;
import com.javalad.habitdeveloper.domain.CheckedHabitHistory;
import com.javalad.habitdeveloper.test.dao.util.AbstractDaoTest;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author KotovDV
 */
public class CheckedHabitHistoryDaoTest extends AbstractDaoTest {

    @Resource
    CheckedHabitHistoryDao checkedHabitHistoryDao;

    private CheckedHabitHistory habitHistory;

    @Before
    public void beforeTest() {
        habitHistory = new CheckedHabitHistory(100, Calendar.getInstance().getTime(), true);
    }


    @Test
    public void addAndGetTest() {
        checkedHabitHistoryDao.add(habitHistory);
        CheckedHabitHistory habitHistoryFromDb = checkedHabitHistoryDao.get(habitHistory.getId());
        assertTrue(EqualsBuilder.reflectionEquals(habitHistory, habitHistoryFromDb));
    }


    @Test
    public void updateTest() {
        checkedHabitHistoryDao.add(habitHistory);
        habitHistory.setCheckedHabitId(200);
        Calendar c = Calendar.getInstance();
        c.setTime(habitHistory.getCheckDate());
        c.add(Calendar.DATE, 1);
        habitHistory.setCheckDate(c.getTime());
        habitHistory.setCheckFlag(false);
        checkedHabitHistoryDao.update(habitHistory);
        CheckedHabitHistory habitHistoryFromDb = checkedHabitHistoryDao.get(habitHistory.getId());
        assertTrue(EqualsBuilder.reflectionEquals(habitHistory, habitHistoryFromDb));
    }

    @Test
    public void deleteAndExistsTest() {
        checkedHabitHistoryDao.add(habitHistory);
        long id = habitHistory.getId();
        assertTrue(id > 0);
        assertTrue(checkedHabitHistoryDao.exists(id));
        checkedHabitHistoryDao.delete(id);
        assertFalse(checkedHabitHistoryDao.exists(id));
    }


    @Test
    public void getAllTest() {
        CheckedHabitHistory firstHabitHistory = new CheckedHabitHistory(100, Calendar.getInstance().getTime(), true);
        CheckedHabitHistory secondHabitHistory = new CheckedHabitHistory(300, Calendar.getInstance().getTime(), true);
        CheckedHabitHistory thirdHabitHistory = new CheckedHabitHistory(200, Calendar.getInstance().getTime(), false);
        List<CheckedHabitHistory> habitHistoryList = Arrays.asList(firstHabitHistory, secondHabitHistory, thirdHabitHistory);
        habitHistoryList.forEach(checkedHabitHistory -> checkedHabitHistoryDao.add(checkedHabitHistory));
        long count = checkedHabitHistoryDao.count();
        assertEquals(count, 3);
        List<CheckedHabitHistory> habitHistoryListFromDb = checkedHabitHistoryDao.getAll();
        habitHistoryListFromDb.forEach(checkedHabitHistory -> assertTrue(habitHistoryList.contains(checkedHabitHistory)));
    }


}
