package com.javalad.habitdeveloper.test.dao;

import com.javalad.habitdeveloper.dao.ProfileDao;
import com.javalad.habitdeveloper.domain.Profile;
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
public class ProfileDaoTest extends AbstractDaoTest {

    @Resource
    private ProfileDao profileDao;

    private Profile profile;

    @Before
    public void beforeTest() {
        profile = new Profile("Test name", "Test description");
    }

    @Test
    public void addAndGetTest() {
        profileDao.add(profile);
        Profile profileFromDb = profileDao.get(profile.getId());
        assertTrue(EqualsBuilder.reflectionEquals(profile, profileFromDb));
    }

    @Test
    public void updateTest() {
        profileDao.add(profile);
        profile.setName("Updated profile value");
        profile.setDescription("Updated description value");
        profileDao.update(profile);
        Profile profileFromDb = profileDao.get(profile.getId());
        assertTrue(EqualsBuilder.reflectionEquals(profile, profileFromDb));
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

}
