package com.javalad.habitdeveloper.test.service;

import com.javalad.habitdeveloper.dao.ProfileDao;
import com.javalad.habitdeveloper.domain.Profile;
import com.javalad.habitdeveloper.exception.ProfileAlreadyExistsException;
import com.javalad.habitdeveloper.service.impl.ProfileServiceImpl;
import com.javalad.habitdeveloper.test.service.util.AbstractMockitoServiceTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

/**
 * @author KotovDV
 */
public class ProfileServiceTest extends AbstractMockitoServiceTest {

    @InjectMocks
    private ProfileServiceImpl profileService;

    @Mock
    private ProfileDao profileDao;

    /**
     * Imitates situation when profile object is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void createNewProfileWithNullObjectTest() throws ProfileAlreadyExistsException {
        Profile profile = null;
        profileService.createNewProfile(profile);
    }

    /**
     *  Imitates situation when profile name field has null value
     */
    @Test(expected = IllegalArgumentException.class)
    public void createNewProfileWithNullNameTest() throws ProfileAlreadyExistsException {
        Profile profile = new Profile(null, null);
        profileService.createNewProfile(profile);
    }

    /**
    *  Imitates situation when profile name field value only contains whitespace symbols
    */
    @Test(expected = IllegalArgumentException.class)
    public void createNewProfileWithWhitespaceNameTest() throws ProfileAlreadyExistsException {
        Profile profile = new Profile("      ", null);
        profileService.createNewProfile(profile);
    }


    /**
     * Imitates situation when user is trying to save new profile with the name that is present in database
     */
    @Test(expected = ProfileAlreadyExistsException.class)
    public void createNewProfileWithExistingNameTest() throws ProfileAlreadyExistsException {
        Profile profile = new Profile("existingName", "description");
        when(profileDao.existsByProfileName("existingName")).thenReturn(true);
        profileService.createNewProfile(profile);
    }


    /**
     * Imitates situation when user successfully saves new profile
     */
    @Test
    public void createNewProfileTest() throws ProfileAlreadyExistsException {
        Profile profile = new Profile("newProfile", "newDescription");
        when(profileDao.existsByProfileName("newProfile")).thenReturn(false);
        when(profileDao.add(profile)).then((Answer<Profile>) invocation -> {
            profile.setId(ThreadLocalRandom.current().nextLong(10)+1);
            return profile;
        });
        Profile resultProfile = profileService.createNewProfile(profile);
        assertTrue(resultProfile.getId().longValue()>0);
    }


    /**
     * Imitates situation when user successfully saves new profile
     * and both profile name and description are being trimmed from whitespace symbols
     */
    @Test
    public void createNewProfileWithTrimTest() throws ProfileAlreadyExistsException {
        String nonTrimmedName = "       newProfile           ";
        String nonTrimmedDescription = "       newDescription           ";
        Profile profile = new Profile(nonTrimmedName, nonTrimmedDescription);
        when(profileDao.existsByProfileName("newProfile")).thenReturn(false);
        when(profileDao.add(profile)).then((Answer<Profile>) invocation -> {
            profile.setId(ThreadLocalRandom.current().nextLong(10)+1);
            return profile;
        });
        Profile resultProfile = profileService.createNewProfile(profile);
        assertEquals(resultProfile.getName(),nonTrimmedName.trim());
        assertEquals(resultProfile.getDescription(),nonTrimmedDescription.trim());
    }


}
