package com.javalad.habitdeveloper.service.impl;

import com.javalad.habitdeveloper.dao.ProfileDao;
import com.javalad.habitdeveloper.domain.Profile;
import com.javalad.habitdeveloper.exception.ProfileAlreadyExistsException;
import com.javalad.habitdeveloper.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author KotovDV
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileDao profileDao;

    @Override
    @Transactional
    public Profile createNewProfile(Profile profile) throws ProfileAlreadyExistsException {
        Assert.notNull(profile, "Profile object must not be null");
        Assert.hasText(profile.getName(), "Profile name field value must not be null and contain at least one non-whitespace symbol");
        String profileName = profile.getName().trim();
        if (!profileDao.existsByProfileName(profileName)) {
            profile.setName(profileName);
            String description = profile.getDescription();
            profile.setDescription(description !=null ? description.trim() : null);
            profileDao.add(profile);
        } else {
            throw new ProfileAlreadyExistsException(profileName);
        }
        return profile;
    }

}