package com.javalad.habitdeveloper.test.dao;

import com.javalad.habitdeveloper.dao.CheckedHabitDao;
import com.javalad.habitdeveloper.domain.CheckedHabit;
import com.javalad.habitdeveloper.test.dao.util.AbstractDaoTest;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author KotovDV
 */
public class CheckedHabitDaoTest extends AbstractDaoTest {

    @Resource
    private CheckedHabitDao checkedHabitDao;

    private CheckedHabit habit;

    @Before
    public void beforeTest() {
        this.habit = new CheckedHabit("addCheckedHabitTest", "description", -5, "* * * * * *");
    }

    @Test
    public void addAndGetTest() {
        checkedHabitDao.add(habit);
        CheckedHabit habitFromDb = checkedHabitDao.get(habit.getId());
        assertTrue(EqualsBuilder.reflectionEquals(habit, habitFromDb));
    }

    @Test
    public void updateTest() {
        checkedHabitDao.add(habit);
        habit.setName("updated value");
        habit.setDescription("updated description");
        habit.setProfileId(-10);
        habit.setCronExpression("1 1 1 1 1 1");
        checkedHabitDao.update(habit);
        CheckedHabit habitFromDb = checkedHabitDao.get(habit.getId());
        assertTrue(EqualsBuilder.reflectionEquals(habit, habitFromDb));
    }

    @Test
    public void deleteAndExistsTest() {
        checkedHabitDao.add(habit);
        long id = habit.getId();
        assertTrue(id > 0);
        assertTrue(checkedHabitDao.exists(id));
        checkedHabitDao.delete(id);
        assertFalse(checkedHabitDao.exists(id));
    }

    @Test
    public void getAllTest() {
        CheckedHabit firstHabit = new CheckedHabit("getAllTest1", "description", -5, "* * * * * *");
        CheckedHabit secondHabit = new CheckedHabit("getAllTest2", "description", -5, "* * * * * *");
        CheckedHabit thirdHabit = new CheckedHabit("getAllTest3", "description", -5, "* * * * * *");
        List<CheckedHabit> checkedHabitList = Arrays.asList(firstHabit, secondHabit, thirdHabit);
        checkedHabitList.forEach(checkedHabit -> checkedHabitDao.add(checkedHabit));

        long count = checkedHabitDao.count();
        assertEquals(count, 3);
        List<CheckedHabit> habitsFromDb = checkedHabitDao.getAll();
        habitsFromDb.forEach(checkedHabit -> assertTrue(checkedHabitList.contains(checkedHabit)));
    }

}
