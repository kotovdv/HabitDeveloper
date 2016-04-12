package com.javalad.habitdeveloper.service;

import com.javalad.habitdeveloper.domain.Profile;
import com.javalad.habitdeveloper.exception.ProfileAlreadyExistsException;

/**
 * @author KotovDV
 */
public interface ProfileService {

    /**
     * Creates new profile with data given in profile object
     * Both name and description fields are trimmed before save
     * <p>
     * Name field must contain at least one non-whitespace symbol
     * <p>
     * Name field is also case-sensitive, which means that profiles with names "MyProfile" and "myProfile"
     * will be both saved to database without ProfileAlreadyExistsException occurrence
     * <p>
     * Description might be left empty
     *
     * @param profile profile object that contains profile information
     * @return profile object with id field filled
     * @throws ProfileAlreadyExistsException if profile with given name already exists in database
     * @throws IllegalArgumentException      if profile object is null
     * @throws IllegalArgumentException      if profile name value is null or it does not contain at least one non-whitespace symbol
     */
    Profile createNewProfile(Profile profile) throws ProfileAlreadyExistsException;

}
