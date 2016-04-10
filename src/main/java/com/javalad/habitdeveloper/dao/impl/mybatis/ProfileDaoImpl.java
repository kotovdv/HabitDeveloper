package com.javalad.habitdeveloper.dao.impl.mybatis;

import com.javalad.habitdeveloper.dao.ProfileDao;
import com.javalad.habitdeveloper.dao.impl.mybatis.mapper.ProfileMapper;
import com.javalad.habitdeveloper.domain.Profile;
import org.springframework.stereotype.Repository;


/**
 * @author KotovDV
 */
@Repository(value = "profileDao")
public class ProfileDaoImpl extends MyBatisAbstractGenericDao<Profile, ProfileMapper, Long> implements ProfileDao {

    public ProfileDaoImpl() {
        super(ProfileMapper.class);
    }

    @Override
    public boolean existsByProfileName(String name) {
        return getMapper().existsByProfileName(name);
    }
}
