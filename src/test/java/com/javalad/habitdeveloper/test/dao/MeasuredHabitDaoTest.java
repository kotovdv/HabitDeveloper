package com.javalad.habitdeveloper.test.dao;

import com.javalad.habitdeveloper.configuration.DataSourceConfiguration;
import com.javalad.habitdeveloper.dao.MeasuredHabitDao;
import com.javalad.habitdeveloper.domain.MeasuredHabit;
import com.javalad.habitdeveloper.test.dao.util.DisableConstraintsListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author KotovDV
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
@SpringApplicationConfiguration(DataSourceConfiguration.class)
@TestExecutionListeners(value = DisableConstraintsListener.class, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public class MeasuredHabitDaoTest {

    @Resource
    private MeasuredHabitDao measuredHabitDao;

    private MeasuredHabit habit;

    @Before
    public void beforeTest() {
        this.habit = new MeasuredHabit("habit", "description", -5, "* * * * * *", Calendar.getInstance().getTime(), 100);
    }

    @Test
    public void getTest() {
        measuredHabitDao.add(habit);
        MeasuredHabit habitFromDb = measuredHabitDao.get(habit.getId());
        assertEquals(habit.getId(), habitFromDb.getId());
        assertEquals(habit.getName(), habitFromDb.getName());
        assertEquals(habit.getDescription(), habitFromDb.getDescription());
        assertEquals(habit.getCronExpression(), habitFromDb.getCronExpression());
        assertEquals(habit.getDeadline(), habitFromDb.getDeadline());
        assertEquals(habit.getDeadlineValue(), habitFromDb.getDeadlineValue(), 0);
    }

    @Test
    public void updateTest() {
        measuredHabitDao.add(habit);
        habit.setName("Updated" + habit.getName());
        habit.setDescription("Updated" + habit.getDescription());
        habit.setCronExpression("Updated" + habit.getCronExpression());
        Calendar c = Calendar.getInstance();
        c.setTime(habit.getDeadline());
        c.add(Calendar.DATE, 1);
        habit.setDeadline(c.getTime());
        habit.setDeadlineValue(habit.getDeadlineValue() + 100);
        measuredHabitDao.update(habit);
        MeasuredHabit habitFromDb = measuredHabitDao.get(habit.getId());
        assertEquals(habit.getId(), habitFromDb.getId());
        assertEquals(habit.getName(), habitFromDb.getName());
        assertEquals(habit.getDescription(), habitFromDb.getDescription());
        assertEquals(habit.getCronExpression(), habitFromDb.getCronExpression());
        assertEquals(habit.getDeadline(), habitFromDb.getDeadline());
        assertEquals(habit.getDeadlineValue(), habitFromDb.getDeadlineValue(), 0);
    }

    @Test
    public void deleteAndExistsTest() {
        measuredHabitDao.add(habit);
        long id = habit.getId();
        assertTrue(id > 0);
        assertTrue(measuredHabitDao.exists(id));
        measuredHabitDao.delete(id);
        assertFalse(measuredHabitDao.exists(id));
    }

    @Test
    public void getAllTest() {
        MeasuredHabit firstHabit = new MeasuredHabit("habit1", "description", -5, "* * * * * *", Calendar.getInstance().getTime(), 100);
        MeasuredHabit secondHabit = new MeasuredHabit("habit2", "description", -5, "* * * * * *", Calendar.getInstance().getTime(), 100);
        MeasuredHabit thirdHabit = new MeasuredHabit("habit3", "description", -5, "* * * * * *", Calendar.getInstance().getTime(), 100);
        measuredHabitDao.add(firstHabit);
        measuredHabitDao.add(secondHabit);
        measuredHabitDao.add(thirdHabit);
        long count = measuredHabitDao.count();
        assertEquals(count, 3);
        List<MeasuredHabit> habits = measuredHabitDao.getAll();
        assertTrue(habits.contains(firstHabit));
        assertTrue(habits.contains(secondHabit));
        assertTrue(habits.contains(thirdHabit));
    }

}
