package com.javalad.habitdeveloper.dao.impl;

import com.javalad.habitdeveloper.dao.CheckedHabitDao;
import com.javalad.habitdeveloper.dao.mapper.CheckedHabitMapper;
import com.javalad.habitdeveloper.dao.util.AbstractGenericDao;
import com.javalad.habitdeveloper.domain.CheckedHabit;
import org.springframework.stereotype.Repository;

/**
 * @author KotovDV
 */
@Repository("checkedHabitDao")
public class CheckedHabitDaoImpl extends AbstractGenericDao<CheckedHabit,CheckedHabitMapper, Long> implements CheckedHabitDao {

    public CheckedHabitDaoImpl() {
        super(CheckedHabitMapper.class);
    }



}
