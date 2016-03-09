package com.javalad.habitdeveloper.test.dao;

import com.javalad.habitdeveloper.configuration.DataSourceConfiguration;
import com.javalad.habitdeveloper.dao.CheckedHabitDao;
import com.javalad.habitdeveloper.domain.CheckedHabit;
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
public class CheckedHabitDaoTest {

    @Resource
    private CheckedHabitDao checkedHabitDao;

    private CheckedHabit habit;

    @Before
    public void beforeTest() {
        this.habit = new CheckedHabit("addCheckedHabitTest", "description", -5, "* * * * * *");
    }

    @Test
    public void getCheckedHabitTest() {
        checkedHabitDao.add(habit);
        CheckedHabit habitFromDb = checkedHabitDao.get(habit.getId());
        assertEquals(habit.getId(), habitFromDb.getId());
        assertEquals(habit.getName(), habitFromDb.getName());
        assertEquals(habit.getDescription(), habitFromDb.getDescription());
        assertEquals(habit.getProfileId(), habitFromDb.getProfileId());
        assertEquals(habit.getCronExpression(), habitFromDb.getCronExpression());
    }

    @Test
    public void updateCheckedHabitTest() {
        checkedHabitDao.add(habit);
        habit.setName("updated value");
        habit.setDescription("updated description");
        habit.setProfileId(-10);
        habit.setCronExpression("1 1 1 1 1 1");
        checkedHabitDao.update(habit);
        CheckedHabit habitFromDb = checkedHabitDao.get(habit.getId());
        assertEquals(habit.getId(), habitFromDb.getId());
        assertEquals(habit.getName(), habitFromDb.getName());
        assertEquals(habit.getDescription(), habitFromDb.getDescription());
        assertEquals(habit.getProfileId(), habitFromDb.getProfileId());
        assertEquals(habit.getCronExpression(), habitFromDb.getCronExpression());
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
        checkedHabitDao.add(firstHabit);
        checkedHabitDao.add(secondHabit);
        checkedHabitDao.add(thirdHabit);
        long count = checkedHabitDao.count();
        assertEquals(count, 3);
        List<CheckedHabit> habits = checkedHabitDao.getAll();
        assertTrue(habits.contains(firstHabit));
        assertTrue(habits.contains(secondHabit));
        assertTrue(habits.contains(thirdHabit));
    }

}
