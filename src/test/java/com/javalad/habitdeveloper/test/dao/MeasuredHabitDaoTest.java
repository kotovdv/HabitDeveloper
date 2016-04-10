package com.javalad.habitdeveloper.test.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.javalad.habitdeveloper.dao.MeasuredHabitDao;
import com.javalad.habitdeveloper.domain.MeasuredHabit;
import com.javalad.habitdeveloper.test.dao.util.AbstractDaoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author KotovDV
 */
public class MeasuredHabitDaoTest extends AbstractDaoTest {

    @Autowired
    private MeasuredHabitDao measuredHabitDao;

    @Test
    @ExpectedDatabase(value = "classpath:dao/MeasuredHabitDaoTest/addTest/after.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void addTest() {
        MeasuredHabit habit = new MeasuredHabit("habit", "description", 1L, "* * * * * *", getTestingDate(2016, 1, 1, 21, 0, 0, 0), 100D);
        measuredHabitDao.add(habit);
        assertEquals(habit.getId().longValue(), 1L);
    }

    @Test
    @DatabaseSetup("classpath:dao/MeasuredHabitDaoTest/getTest/before.xml")
    public void getTest() {
        MeasuredHabit habit = measuredHabitDao.get(1L);
        assertEquals(habit.getId().longValue(), 1L);
        assertEquals(habit.getName(), "habit");
        assertEquals(habit.getDescription(), "description");
        assertEquals(habit.getProfileId().longValue(), 1L);
        assertEquals(habit.getCronExpression(), "* * * * * *");
        assertEquals(habit.getDeadline(), getTestingDate(2016, 1, 1, 21, 0, 0, 0));
        assertEquals(habit.getDeadlineValue(), 100, 0);
    }

    @Test
    @DatabaseSetup("classpath:dao/MeasuredHabitDaoTest/updateTest/before.xml")
    @ExpectedDatabase(value = "classpath:dao/MeasuredHabitDaoTest/updateTest/after.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void updateTest() {
        MeasuredHabit measuredHabit = new MeasuredHabit("updatedHabit", "updatedDescription", 2L, "* * *", getTestingDate(2016, 1, 1, 22, 0, 0, 0), 200D);
        measuredHabit.setId(1L);
        measuredHabitDao.update(measuredHabit);
    }

    @Test
    @DatabaseSetup("classpath:dao/MeasuredHabitDaoTest/deleteTest/before.xml")
    @ExpectedDatabase(value = "classpath:dao/MeasuredHabitDaoTest/deleteTest/after.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteTest() {
        measuredHabitDao.delete(2L);
    }

    @Test
    @DatabaseSetup("classpath:dao/MeasuredHabitDaoTest/countTest/before.xml")
    public void countTest() {
        assertEquals(measuredHabitDao.count(), 3);
    }

    @Test
    @DatabaseSetup("classpath:dao/MeasuredHabitDaoTest/getAllTest/before.xml")
    public void getAllTest() {
        List<MeasuredHabit> habits = measuredHabitDao.getAll();
        assertEquals(habits.size(), 3);
        habits.forEach(habit -> {
            long habitId = habit.getId();
            assertTrue(habitId >= 1L && habitId <= 3L);
            assertEquals(habit.getName(), "habit" + habitId);
            assertEquals(habit.getDescription(), "description" + habitId);
            assertEquals(habit.getProfileId().longValue(),habitId);
            assertEquals(habit.getCronExpression(), "cron" + habitId);
            assertEquals(habit.getDeadline(), getTestingDate(2016, 1, 1, 21, 0, 0, 0));
            assertEquals(habit.getDeadlineValue(), habitId * 100.0, 0);
        });
    }

    @Test
    @DatabaseSetup("classpath:dao/MeasuredHabitDaoTest/existsTest/before.xml")
    public void existsTest() {
        assertTrue(measuredHabitDao.exists(1L));
        assertFalse(measuredHabitDao.exists(25L));
    }


    @Test
    @DatabaseSetup("classpath:dao/MeasuredHabitDaoTest/getHabitsByProfileIdTest/before.xml")
    public void getHabitsByProfileIdTest() {
        List<MeasuredHabit> habits = measuredHabitDao.getHabitsByProfileId(1L);
        assertEquals(habits.size(), 3);
        habits.forEach(habit -> {
            long habitId = habit.getId();
            assertTrue(habitId >= 1L && habitId <= 3L);
            assertEquals(habit.getName(), "habit" + habitId);
            assertEquals(habit.getDescription(), "description" + habitId);
            assertEquals(habit.getProfileId().longValue(), 1L);
            assertEquals(habit.getCronExpression(), "cron" + habitId);
            assertEquals(habit.getDeadline(), getTestingDate(2016, 1, 1, 21, 0, 0, 0));
            assertEquals(habit.getDeadlineValue(), habitId * 100.0, 0);
        });
    }

    @Test
    @DatabaseSetup("classpath:dao/MeasuredHabitDaoTest/getHabitHistoryTest/before.xml")
    public void getHabitHistoryTest() {
        MeasuredHabit habit = measuredHabitDao.get(1L);
        assertEquals(habit.getHabitHistories().size(), 3);
    }


}
