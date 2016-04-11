package com.javalad.habitdeveloper.dao.impl.mybatis;

import com.javalad.habitdeveloper.dao.MeasuredHabitDao;
import com.javalad.habitdeveloper.dao.impl.mybatis.mapper.MeasuredHabitMapper;
import com.javalad.habitdeveloper.domain.MeasuredHabit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author KotovDV
 */
@Repository
public class MeasuredHabitDaoImpl extends MyBatisAbstractGenericDao<MeasuredHabit, MeasuredHabitMapper, Long> implements MeasuredHabitDao {


    public MeasuredHabitDaoImpl() {
        super(MeasuredHabitMapper.class);
    }

    @Override
    public List<MeasuredHabit> getHabitsByProfileId(long profileId) {
        return getMapper().getHabitsByProfileId(profileId);
    }
}
