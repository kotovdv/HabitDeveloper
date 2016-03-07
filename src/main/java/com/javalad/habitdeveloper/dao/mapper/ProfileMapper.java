package com.javalad.habitdeveloper.dao.mapper;

import com.javalad.habitdeveloper.dao.GenericDao;
import com.javalad.habitdeveloper.domain.Profile;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

/**
 * @author KotovDV
 */
public interface ProfileMapper extends GenericDao<Profile,Long> {

    @Override
    @Insert("INSERT INTO PROFILE(name,description) VALUES (#{name},#{description})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    @ResultType(value = Profile.class)
    void add(Profile entity);

    @Override
    @Select("SELECT * FROM profile WHERE id = #{id}")
    Profile get(Long id);

    @Override
    @Update("UPDATE profile SET name=#{name}, description=#{description} WHERE id = #{id}")
    void update(Profile entity);

    @Override
    @Delete("DELETE FROM profile WHERE id = #{id}")
    void delete(Long id);

    @Override
    @Delete("DELETE FROM profile")
    void deleteAll();

    @Override
    @Select("SELECT COUNT(1) counted FROM profile")
    long count();

    @Override
    @Select("SELECT * FROM profile")
    List<Profile> getAll();

    @Override
    @Select("SELECT COUNT(1) counted FROM profile WHERE id = #{id}")
    boolean exists(Long id);

}
