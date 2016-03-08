package com.javalad.habitdeveloper.test.dao;

import com.javalad.habitdeveloper.dao.ProfileDao;
import com.javalad.habitdeveloper.domain.Profile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author KotovDV
 */
@Rollback
@Transactional
public class ProfileDaoTest extends AbstractDaoTest {

    @Resource
    private ProfileDao profileDao;

    private Profile currentProfile;

    @Before
    public void beforeTest(){
        currentProfile =  new Profile("Test name","Test description");
    }

    @Test
    public void addProfileTest() {
        Profile profile = currentProfile;
        profileDao.add(currentProfile);
        assertTrue(profile.getId() != 0L);
    }

    @Test
    public void getProfileTest() {
        Profile profile = currentProfile;
        profileDao.add(profile);
        Profile profileFromDb = profileDao.get(profile.getId());
        assertEquals(profile.getId(), profileFromDb.getId());
        assertEquals(profile.getName(), profileFromDb.getName());
        assertEquals(profile.getDescription(), profileFromDb.getDescription());
    }

    @Test
    public void updateProfileTest() {
        Profile profile = currentProfile;
        profileDao.add(profile);
        profile.setName("Updated profile value");
        profile.setDescription("Updated description value");
        profileDao.update(profile);
        Profile updatedProfile = profileDao.get(profile.getId());
        assertEquals(profile.getId(), updatedProfile.getId());
        assertEquals(profile.getName(), updatedProfile.getName());
        assertEquals(profile.getDescription(), updatedProfile.getDescription());
    }


    @Test
    public void deleteTest() {
        Profile profile = currentProfile;
        profileDao.add(profile);
        long id = profile.getId();
        assertTrue(id > 0);
        assertTrue(profileDao.exists(id));
        profileDao.delete(id);
        assertFalse(profileDao.exists(id));
    }


    @Test
    public void countTest() {
        Profile profile = currentProfile;
        long countBefore = profileDao.count();
        profileDao.add(profile);
        long countAfter = profileDao.count();
        assertEquals(countBefore + 1, countAfter);
    }

    @Test
    public void getAllTest() {
        Profile firstProfile = new Profile("First profile", "First profile");
        Profile secondProfile = new Profile("Second profile", "Second profile");
        Profile thirdProfile = new Profile("Third profile", "Third profile");
        profileDao.add(firstProfile);
        profileDao.add(secondProfile);
        profileDao.add(thirdProfile);
        long count = profileDao.count();
        assertEquals(count, 3);
        List<Profile> profiles = profileDao.getAll();
        assertTrue(profiles.contains(firstProfile));
        assertTrue(profiles.contains(secondProfile));
        assertTrue(profiles.contains(thirdProfile));
    }

    @Test
    public void existsTest() {
        Profile profile = currentProfile;
        profileDao.add(profile);
        assertTrue(profileDao.exists(profile.getId()));
    }


}
