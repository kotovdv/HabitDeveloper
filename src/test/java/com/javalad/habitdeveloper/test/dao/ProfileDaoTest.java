package com.javalad.habitdeveloper.test.dao;

import com.javalad.habitdeveloper.dao.CheckedHabitDao;
import com.javalad.habitdeveloper.dao.MeasuredHabitDao;
import com.javalad.habitdeveloper.dao.ProfileDao;
import com.javalad.habitdeveloper.domain.CheckedHabit;
import com.javalad.habitdeveloper.domain.MeasuredHabit;
import com.javalad.habitdeveloper.domain.Profile;
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
public class ProfileDaoTest extends AbstractDaoTest {

    @Resource
    private ProfileDao profileDao;

    @Resource
    private CheckedHabitDao checkedHabitDao;

    @Resource
    private MeasuredHabitDao measuredHabitDao;

    private Profile profile;

    @Before
    public void beforeTest() {
        profile = new Profile("Test name", "Test description");
    }


    public boolean areProfilesEqual(Profile firstProfile, Profile secondProfile){
         return new EqualsBuilder()
                .append(firstProfile.getId(), secondProfile.getId())
                .append(firstProfile.getName(), secondProfile.getName())
                .append(firstProfile.getDescription(), secondProfile.getDescription())
                .append(firstProfile.getCheckedHabits(), secondProfile.getCheckedHabits())
                .append(firstProfile.getMeasuredHabits(), secondProfile.getMeasuredHabits())
                .isEquals();
    }

    @Test
    public void addAndGetTest() {
        profileDao.add(profile);
        Profile extractedProfile = profileDao.get(profile.getId());
        assertTrue(areProfilesEqual(profile,extractedProfile));
    }

    @Test
    public void updateTest() {
        profileDao.add(profile);
        profile.setName("Updated profile value");
        profile.setDescription("Updated description value");
        profileDao.update(profile);
        Profile profileFromDb = profileDao.get(profile.getId());
        assertTrue(areProfilesEqual(profile, profileFromDb));
    }

    @Test
    public void deleteAndExistsTest() {
        profileDao.add(profile);
        long id = profile.getId();
        assertTrue(id > 0);
        assertTrue(profileDao.exists(id));
        profileDao.delete(id);
        assertFalse(profileDao.exists(id));
    }

    @Test
    public void getAllTest() {
        Profile firstProfile = new Profile("First profile", "First profile");
        Profile secondProfile = new Profile("Second profile", "Second profile");
        Profile thirdProfile = new Profile("Third profile", "Third profile");
        List<Profile> profileList = Arrays.asList(firstProfile, secondProfile, thirdProfile);
        profileList.forEach(profile -> profileDao.add(profile));
        long count = profileDao.count();
        assertEquals(count, 3);
        List<Profile> profilesFromDb = profileDao.getAll();
        profilesFromDb.forEach(profileFromDb -> assertTrue(profileList.contains(profileFromDb)));
    }

    @Test
    public void getProfileHabitsTest(){
        profileDao.add(profile);
        long profileId = profile.getId();

        List<CheckedHabit> initialCheckedHabits = Arrays.asList(new CheckedHabit("getAllTest1", "description", profileId, "* * * * * *"),
                new CheckedHabit("getAllTest2", "description2", profileId, "* * * * * *"));
        List<MeasuredHabit> initialMeasuredHabits = Arrays.asList(new MeasuredHabit("habit1", "description", profileId, "* * * * * *", Calendar.getInstance().getTime(), 100),
                new MeasuredHabit("habit2", "description", profileId, "* * * * * *", Calendar.getInstance().getTime(), 100));

        initialCheckedHabits.forEach(checkedHabit -> checkedHabitDao.add(checkedHabit));
        initialMeasuredHabits.forEach(measuredHabit -> measuredHabitDao.add(measuredHabit));

        Profile profileFromDb = profileDao.get(profileId);

        List<CheckedHabit> extractedCheckedHabits = profileFromDb.getCheckedHabits();
        assertTrue(extractedCheckedHabits.containsAll(initialCheckedHabits) && initialCheckedHabits.containsAll(extractedCheckedHabits));

        List<MeasuredHabit> extractedMeasuredHabits = profileFromDb.getMeasuredHabits();
        assertTrue(extractedMeasuredHabits.containsAll(initialMeasuredHabits) && initialMeasuredHabits.containsAll(extractedMeasuredHabits));
    }

}
