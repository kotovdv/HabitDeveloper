package com.javalad.habitdeveloper.dao.impl.mybatis.mapper;

import com.javalad.habitdeveloper.dao.ProfileDao;
import com.javalad.habitdeveloper.domain.Profile;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author KotovDV
 */
public interface ProfileMapper extends ProfileDao {

    @Override
    @Insert("INSERT INTO PROFILE(name,description) VALUES (#{name},#{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Profile entity);

    @Override
    @Select("SELECT * FROM profile WHERE id = #{id}")
    @ResultMap("profile.profileResultMap")
    Profile get(Long id);

    @Override
    @Update("UPDATE profile SET name=#{name}, description=#{description} WHERE id = #{id}")
    void update(Profile entity);

    @Override
    @Delete("DELETE FROM profile WHERE id = #{id}")
    void delete(Long id);

    @Override
    @Select("SELECT COUNT(1) counted FROM profile")
    long count();

    @Override
    @Select("SELECT * FROM profile")
    @ResultMap("profile.profileResultMap")
    List<Profile> getAll();

    @Override
    @Select("SELECT COUNT(1) counted FROM profile WHERE id = #{id}")
    boolean exists(Long id);

}
