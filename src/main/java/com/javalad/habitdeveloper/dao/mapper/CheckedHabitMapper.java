package com.javalad.habitdeveloper.dao.mapper;

import com.javalad.habitdeveloper.dao.CheckedHabitDao;
import com.javalad.habitdeveloper.domain.CheckedHabit;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author KotovDV
 */
public interface CheckedHabitMapper extends CheckedHabitDao {


    @Override
    @Insert("INSERT INTO CHECKED_HABIT(name,description,profile_id,cron_expression) VALUES(#{name},#{description},#{profileId},#{cronExpression})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(CheckedHabit entity);

    @Override
    @Select("SELECT * FROM CHECKED_HABIT WHERE id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "description",property = "description"),
            @Result(column = "profile_id",property = "profileId"),
            @Result(column = "cron_expression",property = "cronExpression")})
    CheckedHabit get(Long id);

    @Override
    @Update("UPDATE CHECKED_HABIT SET name = #{name}, description =  #{description}, profile_id = #{profileId}, cron_expression = #{cronExpression} WHERE id = #{id}")
    void update(CheckedHabit entity);

    @Override
    @Delete("DELETE FROM CHECKED_HABIT WHERE id = #{id}")
    void delete(Long id);

    @Override
    @Select("SELECT COUNT(1) counted FROM CHECKED_HABIT")
    long count();

    @Override
    @Select("SELECT * FROM CHECKED_HABIT")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "description",property = "description"),
            @Result(column = "profile_id",property = "profileId"),
            @Result(column = "cron_expression",property = "cronExpression")})
    List<CheckedHabit> getAll();

    @Override
    @Select("SELECT COUNT(1) counted FROM CHECKED_HABIT WHERE id = #{id}")
    boolean exists(Long id);
}
