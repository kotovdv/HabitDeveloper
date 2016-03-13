package com.javalad.habitdeveloper.dao.impl.mybatis.mapper;

import com.javalad.habitdeveloper.dao.CheckedHabitHistoryDao;
import com.javalad.habitdeveloper.domain.CheckedHabitHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author KotovDV
 */
public interface CheckedHabitHistoryMapper extends CheckedHabitHistoryDao {


    @Override
    @Insert("INSERT INTO CHECKED_HABIT_HISTORY(checked_habit_id,check_date,check_flag) VALUES(#{checkedHabitId},#{checkDate},#{checkFlag})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(CheckedHabitHistory entity);

    @Override
    @Select("SELECT * FROM CHECKED_HABIT_HISTORY WHERE id = #{id}")
    @ResultMap("checkedHabitHistory.checkedHabitHistoryResultMap")
    CheckedHabitHistory get(Long id);

    @Override
    @Update("UPDATE CHECKED_HABIT_HISTORY SET " +
            " checked_habit_id = #{checkedHabitId}," +
            " check_date =  #{checkDate}," +
            " check_flag = #{checkFlag}" +
            " WHERE id = #{id}")
    void update(CheckedHabitHistory entity);

    @Override
    @Delete("DELETE FROM CHECKED_HABIT_HISTORY WHERE id = #{id}")
    void delete(Long id);

    @Override
    @Select("SELECT COUNT(1) counted FROM CHECKED_HABIT_HISTORY")
    long count();

    @Override
    @Select("SELECT * FROM CHECKED_HABIT_HISTORY")
    @ResultMap("checkedHabitHistory.checkedHabitHistoryResultMap")
    List<CheckedHabitHistory> getAll();

    @Override
    @Select("SELECT COUNT(1) counted FROM CHECKED_HABIT_HISTORY WHERE id = #{id}")
    boolean exists(Long id);

    @Override
    @Select("SELECT * FROM CHECKED_HABIT_HISTORY WHERE checked_habit_id = #{checkedHabitId}")
    @ResultMap("checkedHabitHistory.checkedHabitHistoryResultMap")
    List<CheckedHabitHistory> getByHabitId(long checkedHabitId);
}
