package com.javalad.habitdeveloper.test.dao;

import com.javalad.habitdeveloper.dao.MeasuredHabitDao;
import com.javalad.habitdeveloper.dao.MeasuredHabitHistoryDao;
import com.javalad.habitdeveloper.domain.MeasuredHabit;
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
public class MeasuredHabitDaoTest extends AbstractDaoTest {

    @Resource
    private MeasuredHabitDao measuredHabitDao;

    @Resource
    private MeasuredHabitHistoryDao measuredHabitHistoryDao;

    private MeasuredHabit habit;


    public boolean areHabitsEqual(MeasuredHabit firstHabit, MeasuredHabit secondHabit){
        return new EqualsBuilder()
                .append(firstHabit.getId(), secondHabit.getId())
                .append(firstHabit.getName(), secondHabit.getName())
                .append(firstHabit.getDescription(), secondHabit.getDescription())
                .append(firstHabit.getProfileId(), secondHabit.getProfileId())
                .append(firstHabit.getCronExpression(), secondHabit.getCronExpression())
                .append(firstHabit.getDeadline(),secondHabit.getDeadline())
                .append(firstHabit.getDeadlineValue(),secondHabit.getDeadlineValue())
                .append(firstHabit.getHabitHistories(), secondHabit.getHabitHistories())
                .isEquals();
    }

    @Before
    public void beforeTest() {
        this.habit = new MeasuredHabit("habit", "description", -5, "* * * * * *", Calendar.getInstance().getTime(), 100);
    }

    @Test
    public void addAndGetTest() {
        measuredHabitDao.add(habit);
        MeasuredHabit extractedHabit = measuredHabitDao.get(habit.getId());
        assertTrue(areHabitsEqual(habit, extractedHabit));
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
        MeasuredHabit extractedHabit = measuredHabitDao.get(habit.getId());
        assertTrue(areHabitsEqual(habit, extractedHabit));
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


    @Test
    public void getByProfileId() {
        long profileId = -5;
        List<MeasuredHabit> measuredHabits = Arrays.asList(new MeasuredHabit("habit1", "description", profileId, "* * * * * *", Calendar.getInstance().getTime(), 100),
                new MeasuredHabit("habit2", "description", profileId, "* * * * * *", Calendar.getInstance().getTime(), 100));
        measuredHabits.forEach(checkedHabit -> measuredHabitDao.add(checkedHabit));
        List<MeasuredHabit> measuredHabitsFromDb = measuredHabitDao.getHabitsByProfileId(profileId);
        assertTrue(measuredHabits.containsAll(measuredHabitsFromDb) && measuredHabitsFromDb.containsAll(measuredHabits));
    }


    @Test
    public void getHabitHistoryTest() {
        measuredHabitDao.add(habit);
        long habitId = habit.getId();

        Calendar calendar = Calendar.getInstance();
        MeasuredHabitHistory firstHistory = new MeasuredHabitHistory(habitId, calendar.getTime(), 100.0);
        calendar.add(Calendar.HOUR,24);
        MeasuredHabitHistory secondHistory = new MeasuredHabitHistory(habitId, calendar.getTime(), 200.0);

        List<MeasuredHabitHistory> initialHabitHistory = Arrays.asList(firstHistory, secondHistory);

        initialHabitHistory.forEach(habitHistory -> measuredHabitHistoryDao.add(habitHistory));

        MeasuredHabit extractedHabit = measuredHabitDao.get(habitId);

        List<MeasuredHabitHistory> extractedHabitHistory = extractedHabit.getHabitHistories();
        assertTrue(extractedHabitHistory.containsAll(initialHabitHistory) && initialHabitHistory.containsAll(extractedHabitHistory));

    }

}
