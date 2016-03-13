package com.javalad.habitdeveloper.dao;

import com.javalad.habitdeveloper.domain.MeasuredHabit;

import java.util.List;

/**
 * @author KotovDV
 */
public interface MeasuredHabitDao extends GenericDao<MeasuredHabit, Long> {

    List<MeasuredHabit> getHabitsByProfileId(long profileId);
}
