package com.javalad.habitdeveloper.test.dao;

import com.javalad.habitdeveloper.dao.MeasuredHabitDao;
import com.javalad.habitdeveloper.domain.MeasuredHabit;
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
public class MeasuredHabitDaoTest extends AbstractDaoTest {

    @Resource
    private MeasuredHabitDao measuredHabitDao;

    private MeasuredHabit habit;

    @Before
    public void beforeTest() {
        this.habit = new MeasuredHabit("habit", "description", -5, "* * * * * *", Calendar.getInstance().getTime(), 100);
    }

    @Test
    public void addAndGetTest() {
        measuredHabitDao.add(habit);
        MeasuredHabit habitFromDb = measuredHabitDao.get(habit.getId());
        assertTrue(EqualsBuilder.reflectionEquals(habit, habitFromDb));
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
        assertTrue(EqualsBuilder.reflectionEquals(habit, habitFromDb));
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
        List<MeasuredHabit> measuredHabitList = Arrays.asList(firstHabit, secondHabit, thirdHabit);
        measuredHabitList.forEach(measuredHabit -> measuredHabitDao.add(measuredHabit));
        long count = measuredHabitDao.count();
        assertEquals(count, 3);
        List<MeasuredHabit> habitsFromDb = measuredHabitDao.getAll();
        habitsFromDb.forEach(measuredHabit -> assertTrue(measuredHabitList.contains(measuredHabit)));
    }

}
