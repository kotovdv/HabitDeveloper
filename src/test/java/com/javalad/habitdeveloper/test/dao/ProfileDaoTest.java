package com.javalad.habitdeveloper.test.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.javalad.habitdeveloper.dao.ProfileDao;
import com.javalad.habitdeveloper.domain.Profile;
import com.javalad.habitdeveloper.test.dao.util.AbstractDaoTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author KotovDV
 */
public class ProfileDaoTest extends AbstractDaoTest {

    @Resource
    private ProfileDao profileDao;

    @Test
    @ExpectedDatabase(value = "classpath:dao/ProfileDaoTest/addTest/result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void addTest() {
        Profile profile = new Profile("New test", "New description");
        profileDao.add(profile);
    }

    @Test
    @DatabaseSetup(value = "classpath:dao/ProfileDaoTest/getTest/initial.xml")
    public void getTest() {
        Profile extractedProfile = profileDao.get(1L);
        assertEquals(extractedProfile.getId(), 1);
        assertEquals(extractedProfile.getName(), "getTest");
        assertEquals(extractedProfile.getDescription(), "getTestDescription");
    }

    @Test
    @DatabaseSetup(value = "classpath:dao/ProfileDaoTest/updateTest/initial.xml")
    @ExpectedDatabase(value = "classpath:dao/ProfileDaoTest/updateTest/result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void updateTest() {
        Profile extractedProfile = new Profile("updatedName", "updatedDescription");
        extractedProfile.setId(1L);
        profileDao.update(extractedProfile);
    }

    @Test
    @DatabaseSetup(value = "classpath:dao/ProfileDaoTest/deleteTest/initial.xml")
    @ExpectedDatabase(value = "classpath:dao/ProfileDaoTest/deleteTest/result.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteTest() {
        profileDao.delete(2L);
    }

    @Test
    @DatabaseSetup(value = "classpath:dao/ProfileDaoTest/countTest/initial.xml")
    public void countTest() {
        assertEquals(profileDao.count(), 3);
    }

    @Test
    @DatabaseSetup(value = "classpath:dao/ProfileDaoTest/getAllTest/initial.xml")
    public void getAllTest() {
        List<Profile> profiles = profileDao.getAll();
        assertEquals(profiles.size(), 3);

        profiles.forEach(profile -> {
            long profileId = profile.getId();
            assertTrue(profileId >= 1 && profileId <= 3);
            assertEquals(profile.getName(), "test" + profileId);
            assertEquals(profile.getDescription(), "description" + profileId);
        });
    }


    @Test
    @DatabaseSetup(value = "classpath:dao/ProfileDaoTest/getProfileHabitsTest/initial.xml")
    public void getProfileHabitsTest() {
        Profile extractedProfile = profileDao.get(1L);
        assertEquals(extractedProfile.getCheckedHabits().size(), 2);
        assertEquals(extractedProfile.getMeasuredHabits().size(), 2);
    }
}
