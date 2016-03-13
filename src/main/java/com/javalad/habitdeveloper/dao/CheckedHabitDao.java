package com.javalad.habitdeveloper.dao;

import com.javalad.habitdeveloper.domain.CheckedHabit;

import java.util.List;

/**
 * @author KotovDV
 */
public interface CheckedHabitDao extends GenericDao<CheckedHabit,Long> {

    List<CheckedHabit> getHabitsByProfileId(long profileId);

}
