package com.javalad.habitdeveloper.dao.impl.mybatis.mapper;

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
    @ResultMap("measuredHabit.measuredHabitResultMap")
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
    @ResultMap("measuredHabit.measuredHabitResultMap")
    List<MeasuredHabit> getAll();

    @Override
    @Select("SELECT COUNT(1) counted FROM MEASURED_HABIT WHERE id = #{id}")
    boolean exists(Long id);


    @Override
    @Select("SELECT * FROM MEASURED_HABIT WHERE profile_id = #{profileId}")
    @ResultMap("measuredHabit.measuredHabitResultMap")
    List<MeasuredHabit> getHabitsByProfileId(long profileId);

}
