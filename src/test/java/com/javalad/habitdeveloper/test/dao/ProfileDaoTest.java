package com.javalad.habitdeveloper.test.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.javalad.habitdeveloper.dao.ProfileDao;
import com.javalad.habitdeveloper.domain.Profile;
import com.javalad.habitdeveloper.test.dao.util.AbstractDaoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author KotovDV
 */
public class ProfileDaoTest extends AbstractDaoTest {

    @Autowired
    private ProfileDao profileDao;

    @Test
    @ExpectedDatabase(value = "classpath:dao/ProfileDaoTest/addTest/after.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void addTest() {
        Profile profile = new Profile("New test", "New description");
        profileDao.add(profile);
        assertEquals(profile.getId(), 1);
    }

    @Test
    @DatabaseSetup("classpath:dao/ProfileDaoTest/getTest/before.xml")
    public void getTest() {
        Profile extractedProfile = profileDao.get(1L);
        assertEquals(extractedProfile.getId(), 1);
        assertEquals(extractedProfile.getName(), "getTest");
        assertEquals(extractedProfile.getDescription(), "getTestDescription");
    }

    @Test
    @DatabaseSetup("classpath:dao/ProfileDaoTest/updateTest/before.xml")
    @ExpectedDatabase(value = "classpath:dao/ProfileDaoTest/updateTest/after.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void updateTest() {
        Profile extractedProfile = new Profile("updatedName", "updatedDescription");
        extractedProfile.setId(1L);
        profileDao.update(extractedProfile);
    }

    @Test
    @DatabaseSetup("classpath:dao/ProfileDaoTest/deleteTest/before.xml")
    @ExpectedDatabase(value = "classpath:dao/ProfileDaoTest/deleteTest/after.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteTest() {
        profileDao.delete(2L);
    }

    @Test
    @DatabaseSetup("classpath:dao/ProfileDaoTest/countTest/before.xml")
    public void countTest() {
        assertEquals(profileDao.count(), 3);
    }

    @Test
    @DatabaseSetup("classpath:dao/ProfileDaoTest/getAllTest/before.xml")
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
    @DatabaseSetup("classpath:dao/ProfileDaoTest/getProfileHabitsTest/before.xml")
    public void getProfileHabitsTest() {
        Profile extractedProfile = profileDao.get(1L);
        assertEquals(extractedProfile.getCheckedHabits().size(), 2);
        assertEquals(extractedProfile.getMeasuredHabits().size(), 2);
    }

    @Test
    @DatabaseSetup("classpath:dao/ProfileDaoTest/getProfileHabitsTest/before.xml")
    public void existsTest() {
        assertTrue(profileDao.exists(1L));
        assertFalse(profileDao.exists(25L));
    }

    @Test
    @DatabaseSetup("classpath:dao/ProfileDaoTest/existsByProfileNameTest/before.xml")
    public void existsByProfileNameTest(){
        assertTrue(profileDao.existsByProfileName("test1"));
        assertFalse(profileDao.existsByProfileName("test2"));
    }
}
