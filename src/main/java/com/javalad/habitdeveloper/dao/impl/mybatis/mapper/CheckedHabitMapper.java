package com.javalad.habitdeveloper.dao.impl.mybatis.mapper;

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
    @ResultMap("checkedHabit.checkedHabitResultMap")
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
    @ResultMap("checkedHabit.checkedHabitResultMap")
    List<CheckedHabit> getAll();

    @Override
    @Select("SELECT COUNT(1) counted FROM CHECKED_HABIT WHERE id = #{id}")
    boolean exists(Long id);

    @Override
    @Select("SELECT * FROM CHECKED_HABIT WHERE profile_id = #{profileId}")
    @ResultMap("checkedHabit.checkedHabitResultMap")
    List<CheckedHabit> getHabitsByProfileId(long profileId);
}
