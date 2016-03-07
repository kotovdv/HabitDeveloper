package com.javalad.habitdeveloper.dao.impl;

import com.javalad.habitdeveloper.dao.ProfileDao;
import com.javalad.habitdeveloper.dao.mapper.ProfileMapper;
import com.javalad.habitdeveloper.domain.Profile;
import org.springframework.stereotype.Repository;


/**
 * @author KotovDV
 */
@Repository
public class ProfileDaoImpl extends AbstractGenericDao<Profile, ProfileMapper, Long> implements ProfileDao {

    public ProfileDaoImpl() {
        super(ProfileMapper.class);
    }

}
