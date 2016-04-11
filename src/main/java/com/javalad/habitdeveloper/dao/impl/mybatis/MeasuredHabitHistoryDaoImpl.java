package com.javalad.habitdeveloper.dao.impl.mybatis;

import com.javalad.habitdeveloper.dao.MeasuredHabitHistoryDao;
import com.javalad.habitdeveloper.dao.impl.mybatis.mapper.MeasuredHabitHistoryMapper;
import com.javalad.habitdeveloper.domain.MeasuredHabitHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author KotovDV
 */
@Repository
public class MeasuredHabitHistoryDaoImpl extends MyBatisAbstractGenericDao<MeasuredHabitHistory, MeasuredHabitHistoryMapper, Long> implements MeasuredHabitHistoryDao {

    public MeasuredHabitHistoryDaoImpl() {
        super(MeasuredHabitHistoryMapper.class);
    }

    @Override
    public List<MeasuredHabitHistory> getByHabitId(long measuredHabitId) {
        return getMapper().getByHabitId(measuredHabitId);
    }
}
