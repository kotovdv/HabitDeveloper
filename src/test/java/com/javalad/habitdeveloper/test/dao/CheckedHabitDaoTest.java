package com.javalad.habitdeveloper.test.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.javalad.habitdeveloper.dao.CheckedHabitDao;
import com.javalad.habitdeveloper.domain.CheckedHabit;
import com.javalad.habitdeveloper.test.dao.util.AbstractDaoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author KotovDV
 */
public class CheckedHabitDaoTest extends AbstractDaoTest {

    @Autowired
    private CheckedHabitDao checkedHabitDao;

    @Test
    @ExpectedDatabase(value = "classpath:dao/CheckedHabitDaoTest/addTest/after.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void addTest() {
        CheckedHabit habit = new CheckedHabit("habit1", "description1", 1L, "* * * * * *");
        checkedHabitDao.add(habit);
        assertEquals(habit.getId().longValue(), 1L);
    }

    @Test
    @DatabaseSetup("classpath:dao/CheckedHabitDaoTest/getTest/before.xml")
    public void getTest() {
        CheckedHabit habit = checkedHabitDao.get(1L);
        assertEquals(habit.getId().longValue(), 1L);
        assertEquals(habit.getName(), "habit1");
        assertEquals(habit.getDescription(), "description1");
        assertEquals(habit.getProfileId().longValue(), 1L);
        assertEquals(habit.getCronExpression(), "* * * * * *");
    }

    @Test
    @DatabaseSetup("classpath:dao/CheckedHabitDaoTest/updateTest/before.xml")
    @ExpectedDatabase(value = "classpath:dao/CheckedHabitDaoTest/updateTest/after.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void updateTest() {
        CheckedHabit habit = new CheckedHabit("habit1", "description1", 1L, "* * * * * *");
        habit.setId(1L);
        habit.setName("updatedHabit");
        habit.setDescription("updatedDescription");
        habit.setProfileId(5L);
        habit.setCronExpression("* * *");
        checkedHabitDao.update(habit);
    }

    @Test
    @DatabaseSetup("classpath:dao/CheckedHabitDaoTest/deleteTest/before.xml")
    @ExpectedDatabase(value = "classpath:dao/CheckedHabitDaoTest/deleteTest/after.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteTest() {
        checkedHabitDao.delete(2L);
    }

    @Test
    @DatabaseSetup("classpath:dao/CheckedHabitDaoTest/countTest/before.xml")
    public void countTest() {
        assertEquals(checkedHabitDao.count(), 3);
    }

    @Test
    @DatabaseSetup("classpath:dao/CheckedHabitDaoTest/getAllTest/before.xml")
    public void getAllTest() {
        List<CheckedHabit> habits = checkedHabitDao.getAll();
        assertEquals(habits.size(), 3);
        habits.forEach(habit -> {
            long habitId = habit.getId();
            assertTrue(habitId >= 1L && habitId <= 3L);
            assertEquals(habit.getName(), "habit" + habitId);
            assertEquals(habit.getDescription(), "description" + habitId);
            assertEquals(habit.getProfileId().longValue(), habitId + 3);
            assertEquals(habit.getCronExpression(), "cron" + habitId);
        });
    }


    @Test
    @DatabaseSetup("classpath:dao/CheckedHabitDaoTest/existsTest/before.xml")
    public void existsTest() {
        assertTrue(checkedHabitDao.exists(1L));
        assertFalse(checkedHabitDao.exists(25L));
    }

    @Test
    @DatabaseSetup("classpath:dao/CheckedHabitDaoTest/getHabitsByProfileIdTest/before.xml")
    public void getHabitsByProfileIdTest() {
        List<CheckedHabit> habits = checkedHabitDao.getHabitsByProfileId(1L);
        assertEquals(habits.size(), 3);
        habits.forEach(habit -> {
            long habitId = habit.getId();
            assertTrue(habitId >= 1L && habitId <= 3L);
            assertEquals(habit.getName(), "habit" + habitId);
            assertEquals(habit.getDescription(), "description" + habitId);
            assertEquals(habit.getProfileId().longValue(), 1L);
            assertEquals(habit.getCronExpression(), "cron" + habitId);
        });
    }

    @Test
    @DatabaseSetup("classpath:dao/CheckedHabitDaoTest/getHabitHistoryTest/before.xml")
    public void getHabitHistoryTest() throws Exception {
        CheckedHabit habit = checkedHabitDao.get(1L);
        assertEquals(habit.getHabitHistories().size(), 3);
    }

}
