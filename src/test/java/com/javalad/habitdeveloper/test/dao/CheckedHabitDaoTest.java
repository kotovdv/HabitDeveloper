package com.javalad.habitdeveloper.test.dao;

import com.javalad.habitdeveloper.dao.CheckedHabitDao;
import com.javalad.habitdeveloper.dao.CheckedHabitHistoryDao;
import com.javalad.habitdeveloper.domain.CheckedHabit;
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
public class CheckedHabitDaoTest extends AbstractDaoTest {

    @Resource
    private CheckedHabitDao checkedHabitDao;

    @Resource
    private CheckedHabitHistoryDao checkedHabitHistoryDao;

    private CheckedHabit habit;

    @Before
    public void beforeTest() {
        this.habit = new CheckedHabit("addCheckedHabitTest", "description", -5, "* * * * * *");
    }

    public boolean areHabitsEqual(CheckedHabit firstHabit, CheckedHabit secondHabit){
        return new EqualsBuilder()
                .append(firstHabit.getId(), secondHabit.getId())
                .append(firstHabit.getName(), secondHabit.getName())
                .append(firstHabit.getDescription(), secondHabit.getDescription())
                .append(firstHabit.getProfileId(), secondHabit.getProfileId())
                .append(firstHabit.getCronExpression(), secondHabit.getCronExpression())
                .append(firstHabit.getHabitHistories(),secondHabit.getHabitHistories())
                .isEquals();
    }

    @Test
    public void addAndGetTest() {
        checkedHabitDao.add(habit);
        CheckedHabit extractedHabit = checkedHabitDao.get(habit.getId());
        assertTrue(areHabitsEqual(habit, extractedHabit));
    }

    @Test
    public void updateTest() {
        checkedHabitDao.add(habit);
        habit.setName("updated value");
        habit.setDescription("updated description");
        habit.setProfileId(-10);
        habit.setCronExpression("1 1 1 1 1 1");
        checkedHabitDao.update(habit);
        CheckedHabit extractedHabit = checkedHabitDao.get(habit.getId());
        assertTrue(areHabitsEqual(habit, extractedHabit));
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

    @Test
    public void getByProfileId(){
        long profileId = -5;
        List<CheckedHabit> checkedHabits = Arrays.asList(new CheckedHabit("getAllTest1", "description", profileId, "* * * * * *"),
                                                         new CheckedHabit("getAllTest2", "description2", profileId, "* * * * * *"));
        checkedHabits.forEach(checkedHabit -> checkedHabitDao.add(checkedHabit));
        List<CheckedHabit> checkedHabitsFromDb = checkedHabitDao.getHabitsByProfileId(profileId);
        assertTrue(checkedHabits.containsAll(checkedHabitsFromDb) && checkedHabitsFromDb.containsAll(checkedHabits));
    }



    @Test
    public void getHabitHistoryTest() {
        checkedHabitDao.add(habit);
        long habitId = habit.getId();

        Calendar calendar = Calendar.getInstance();
        CheckedHabitHistory firstHistory = new CheckedHabitHistory(habitId, calendar.getTime(), true);
        calendar.add(Calendar.HOUR,24);
        CheckedHabitHistory secondHistory = new CheckedHabitHistory(habitId, calendar.getTime(), false);

        List<CheckedHabitHistory> initialHabitHistory = Arrays.asList(firstHistory,secondHistory);

        initialHabitHistory.forEach(habitHistory -> checkedHabitHistoryDao.add(habitHistory));

        CheckedHabit extractedHabit = checkedHabitDao.get(habitId);

        List<CheckedHabitHistory> extractedHabitHistory = extractedHabit.getHabitHistories();
        assertTrue(extractedHabitHistory.containsAll(initialHabitHistory) && initialHabitHistory.containsAll(extractedHabitHistory));

    }

}
