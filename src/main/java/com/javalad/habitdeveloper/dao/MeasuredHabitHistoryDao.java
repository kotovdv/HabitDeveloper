package com.javalad.habitdeveloper.dao;

import com.javalad.habitdeveloper.domain.MeasuredHabitHistory;

import java.util.List;

/**
 * @author KotovDV
 */
public interface MeasuredHabitHistoryDao extends GenericDao<MeasuredHabitHistory, Long> {

    List<MeasuredHabitHistory> getByHabitId(long measuredHabitId);
}
