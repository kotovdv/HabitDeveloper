package com.javalad.habitdeveloper.dao.impl.mybatis;

import com.javalad.habitdeveloper.dao.impl.mybatis.mapper.CheckedHabitMapper;
import com.javalad.habitdeveloper.domain.CheckedHabit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author KotovDV
 */
@Repository("checkedHabitDao")
public class CheckedHabitDaoImpl extends MyBatisAbstractGenericDao<CheckedHabit,CheckedHabitMapper, Long> implements com.javalad.habitdeveloper.dao.CheckedHabitDao {

    public CheckedHabitDaoImpl() {
        super(CheckedHabitMapper.class);
    }

    @Override
    public List<CheckedHabit> getHabitsByProfileId(long profileId) {
        return getMapper().getHabitsByProfileId(profileId);
    }
}
