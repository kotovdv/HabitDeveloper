package com.javalad.habitdeveloper.dao.impl;

import com.javalad.habitdeveloper.dao.MeasuredHabitDao;
import com.javalad.habitdeveloper.dao.mapper.MeasuredHabitMapper;
import com.javalad.habitdeveloper.dao.util.AbstractGenericDao;
import com.javalad.habitdeveloper.domain.MeasuredHabit;
import org.springframework.stereotype.Repository;

/**
 * @author KotovDV
 */
@Repository(value = "measuredHabitDao")
public class MeasuredHabitDaoImpl extends AbstractGenericDao<MeasuredHabit, MeasuredHabitMapper, Long> implements MeasuredHabitDao {


    public MeasuredHabitDaoImpl() {
        super(MeasuredHabitMapper.class);
    }
}
