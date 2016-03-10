package com.javalad.habitdeveloper.dao.impl.mybatis.mapper;

import com.javalad.habitdeveloper.dao.MeasuredHabitHistoryDao;
import com.javalad.habitdeveloper.domain.MeasuredHabitHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author KotovDV
 */
public interface MeasuredHabitHistoryMapper extends MeasuredHabitHistoryDao {


    @Override
    @Insert("INSERT INTO MEASURED_HABIT_HISTORY(measured_habit_id,check_date,measured_value) VALUES(#{measuredHabitId},#{checkDate},#{measuredValue})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(MeasuredHabitHistory entity);

    @Override
    @Select("SELECT * FROM MEASURED_HABIT_HISTORY WHERE id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "measured_habit_id",property = "measuredHabitId"),
            @Result(column = "check_date",property = "checkDate"),
            @Result(column = "measured_value",property = "measuredValue")})
    MeasuredHabitHistory get(Long id);

    @Override
    @Update("UPDATE MEASURED_HABIT_HISTORY SET " +
            " measured_habit_id = #{measuredHabitId}," +
            " check_date =  #{checkDate}," +
            " measured_value = #{measuredValue}" +
            " WHERE id = #{id}")
    void update(MeasuredHabitHistory entity);

    @Override
    @Delete("DELETE FROM MEASURED_HABIT_HISTORY WHERE id = #{id}")
    void delete(Long id);

    @Override
    @Select("SELECT COUNT(1) counted FROM MEASURED_HABIT_HISTORY")
    long count();

    @Override
    @Select("SELECT * FROM MEASURED_HABIT_HISTORY")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "measured_habit_id",property = "measuredHabitId"),
            @Result(column = "check_date",property = "checkDate"),
            @Result(column = "measured_value",property = "measuredValue")})
    List<MeasuredHabitHistory> getAll();

    @Override
    @Select("SELECT COUNT(1) counted FROM MEASURED_HABIT_HISTORY WHERE id = #{id}")
    boolean exists(Long id);
}
