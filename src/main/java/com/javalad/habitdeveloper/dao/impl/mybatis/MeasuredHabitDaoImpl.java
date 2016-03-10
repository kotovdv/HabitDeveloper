package com.javalad.habitdeveloper.dao.impl.mybatis;

import com.javalad.habitdeveloper.dao.impl.mybatis.mapper.MeasuredHabitMapper;
import com.javalad.habitdeveloper.domain.MeasuredHabit;
import org.springframework.stereotype.Repository;

/**
 * @author KotovDV
 */
@Repository(value = "measuredHabitDao")
public class MeasuredHabitDaoImpl extends MyBatisAbstractGenericDao<MeasuredHabit, MeasuredHabitMapper, Long> implements com.javalad.habitdeveloper.dao.MeasuredHabitDao {


    public MeasuredHabitDaoImpl() {
        super(MeasuredHabitMapper.class);
    }
}
