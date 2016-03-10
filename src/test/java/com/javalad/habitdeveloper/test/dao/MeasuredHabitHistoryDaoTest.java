package com.javalad.habitdeveloper.test.dao;

import com.javalad.habitdeveloper.dao.MeasuredHabitHistoryDao;
import com.javalad.habitdeveloper.domain.MeasuredHabitHistory;
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
public class MeasuredHabitHistoryDaoTest extends AbstractDaoTest {

    @Resource
    MeasuredHabitHistoryDao measuredHabitHistoryDao;

    private MeasuredHabitHistory habitHistory;

    @Before
    public void beforeTest() {
        habitHistory = new MeasuredHabitHistory(100, Calendar.getInstance().getTime(), 150.0);
    }


    @Test
    public void addAndGetTest() {
        measuredHabitHistoryDao.add(habitHistory);
        MeasuredHabitHistory habitHistoryFromDb = measuredHabitHistoryDao.get(habitHistory.getId());
        assertTrue(EqualsBuilder.reflectionEquals(habitHistory, habitHistoryFromDb));
    }

    @Test
    public void updateTest() {
        measuredHabitHistoryDao.add(habitHistory);
        habitHistory.setMeasuredHabitId(200);
        Calendar c = Calendar.getInstance();
        c.setTime(habitHistory.getCheckDate());
        c.add(Calendar.DATE, 1);
        habitHistory.setCheckDate(c.getTime());
        habitHistory.setMeasuredValue(350.0);
        measuredHabitHistoryDao.update(habitHistory);
        MeasuredHabitHistory habitHistoryFromDb = measuredHabitHistoryDao.get(habitHistory.getId());
        assertTrue(EqualsBuilder.reflectionEquals(habitHistory, habitHistoryFromDb));
    }


    @Test
    public void deleteAndExistsTest() {
        measuredHabitHistoryDao.add(habitHistory);
        long id = habitHistory.getId();
        assertTrue(id > 0);
        assertTrue(measuredHabitHistoryDao.exists(id));
        measuredHabitHistoryDao.delete(id);
        assertFalse(measuredHabitHistoryDao.exists(id));
    }


    @Test
    public void getAllTest() {
        MeasuredHabitHistory firstHabitHistory = new MeasuredHabitHistory(100, Calendar.getInstance().getTime(), 100.0);
        MeasuredHabitHistory secondHabitHistory = new MeasuredHabitHistory(300, Calendar.getInstance().getTime(), 200.0);
        MeasuredHabitHistory thirdHabitHistory = new MeasuredHabitHistory(200, Calendar.getInstance().getTime(), 300.0);
        List<MeasuredHabitHistory> habitHistoryList = Arrays.asList(firstHabitHistory, secondHabitHistory, thirdHabitHistory);
        habitHistoryList.forEach(checkedHabitHistory -> measuredHabitHistoryDao.add(checkedHabitHistory));
        long count = measuredHabitHistoryDao.count();
        assertEquals(count, 3);
        List<MeasuredHabitHistory> habitHistoryListFromDb = measuredHabitHistoryDao.getAll();
        habitHistoryListFromDb.forEach(checkedHabitHistory -> assertTrue(habitHistoryList.contains(checkedHabitHistory)));
    }


}
