package com.javalad.habitdeveloper.dao.mapper;

import com.javalad.habitdeveloper.dao.MeasuredHabitDao;
import com.javalad.habitdeveloper.domain.MeasuredHabit;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author KotovDV
 */
public interface MeasuredHabitMapper extends MeasuredHabitDao {

    @Override
    @Insert("INSERT INTO MEASURED_HABIT" +
            "(name,description,profile_id,cron_expression,deadline,deadline_value) VALUES" +
            "(#{name},#{description},#{profileId},#{cronExpression},#{deadline},#{deadlineValue})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(MeasuredHabit entity);

    @Override
    @Select("SELECT * FROM MEASURED_HABIT WHERE id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "description", property = "description"),
            @Result(column = "profile_id", property = "profileId"),
            @Result(column = "cron_expression", property = "cronExpression"),
            @Result(column = "deadline", property = "deadline"),
            @Result(column = "deadline_value", property = "deadlineValue")})
    MeasuredHabit get(Long id);

    @Override
    @Update("UPDATE MEASURED_HABIT SET name = #{name}," +
            " description =  #{description}," +
            " profile_id = #{profileId}," +
            " cron_expression = #{cronExpression}, " +
            " deadline = #{deadline}, " +
            " deadline_value = #{deadlineValue} " +
            "WHERE id = #{id}")
    void update(MeasuredHabit entity);

    @Override
    @Delete("DELETE FROM MEASURED_HABIT WHERE id = #{id}")
    void delete(Long id);

    @Override
    @Select("SELECT COUNT(1) counted FROM MEASURED_HABIT")
    long count();

    @Override
    @Select("SELECT * FROM MEASURED_HABIT")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "description", property = "description"),
            @Result(column = "profile_id", property = "profileId"),
            @Result(column = "cron_expression", property = "cronExpression"),
            @Result(column = "deadline", property = "deadline"),
            @Result(column = "deadline_value", property = "deadlineValue")})
    List<MeasuredHabit> getAll();

    @Override
    @Select("SELECT COUNT(1) counted FROM MEASURED_HABIT WHERE id = #{id}")
    boolean exists(Long id);
}
