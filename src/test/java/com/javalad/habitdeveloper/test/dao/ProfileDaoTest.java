package com.javalad.habitdeveloper.test.dao;

import com.javalad.habitdeveloper.dao.ProfileDao;
import com.javalad.habitdeveloper.domain.Profile;
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
    public void addProfileTest() {
        Profile profile = new Profile("Add test", "Add test");
        profileDao.add(profile);
        assertTrue(profile.getId() != 0L);
    }

    @Test
    public void getProfileTest() {
        Profile profile = new Profile("Get test", "Get test");
        profileDao.add(profile);
        Profile profileFromDb = profileDao.get(profile.getId());
        assertEquals(profile.getId(), profileFromDb.getId());
        assertEquals(profile.getName(), profileFromDb.getName());
        assertEquals(profile.getDescription(), profileFromDb.getDescription());
    }

    @Test
    public void updateProfileTest(){
        Profile profile =  new Profile("Update test","Update test");
        profileDao.add(profile);
        profile.setName("Updated profile value");
        profile.setDescription("Updated description value");
        profileDao.update(profile);
        Profile updatedProfile = profileDao.get(profile.getId());
        assertEquals(profile.getId(),updatedProfile.getId());
        assertEquals(profile.getName(),updatedProfile.getName());
        assertEquals(profile.getDescription(),updatedProfile.getDescription());
    }


    @Test
    public void deleteTest(){
        Profile profile =  new Profile("Delete test","Delete test");
        profileDao.add(profile);
        profileDao.delete(profile.getId());
        Profile deletedProfile = profileDao.get(profile.getId());
        assertNull(deletedProfile);
    }


    @Test
    public void countTest(){
        Profile profile =  new Profile("Count test","Count test");
        long countBefore = profileDao.count();
        profileDao.add(profile);
        long countAfter  = profileDao.count();
        assertEquals(countBefore+1,countAfter);
    }


    @Test
    public void deleteAllTest(){
        profileDao.add(new Profile("Delete all test","Delete all test"));
        long count = profileDao.count();
        profileDao.deleteAll();
        long afterCount = profileDao.count();
        assertTrue(count>0);
        assertEquals(afterCount,0);
    }

    @Test
    public void getAllTest(){
        profileDao.deleteAll();
        Profile firstProfile = new Profile("First profile", "First profile");
        Profile secondProfile = new Profile("Second profile", "Second profile");
        Profile thirdProfile = new Profile("Third profile", "Third profile");
        profileDao.add(firstProfile);
        profileDao.add(secondProfile);
        profileDao.add(thirdProfile);
        long count = profileDao.count();
        assertEquals(count,3);
        List<Profile> profiles = profileDao.getAll();
        assertTrue(profiles.contains(firstProfile));
        assertTrue(profiles.contains(secondProfile));
        assertTrue(profiles.contains(thirdProfile));
    }


    @Test
    public void existsTest(){
        Profile entity = new Profile("Exists test", "Exists test");
        profileDao.add(entity);
        assertTrue(profileDao.exists(entity.getId()));
    }




}
