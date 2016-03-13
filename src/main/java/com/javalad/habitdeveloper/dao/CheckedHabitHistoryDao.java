package com.javalad.habitdeveloper.dao;

import com.javalad.habitdeveloper.domain.CheckedHabitHistory;

import java.util.List;

/**
 * @author KotovDV
 */
public interface CheckedHabitHistoryDao extends GenericDao<CheckedHabitHistory,Long> {

    List<CheckedHabitHistory> getByHabitId(long checkedHabitId);
}
