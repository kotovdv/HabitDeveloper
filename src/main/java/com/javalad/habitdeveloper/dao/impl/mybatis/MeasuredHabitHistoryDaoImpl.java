package com.javalad.habitdeveloper.dao.impl.mybatis;

import com.javalad.habitdeveloper.dao.impl.mybatis.mapper.MeasuredHabitHistoryMapper;
import com.javalad.habitdeveloper.domain.MeasuredHabitHistory;
import org.springframework.stereotype.Repository;

/**
 * @author KotovDV
 */
@Repository("measuredHabitHistoryDao")
public class MeasuredHabitHistoryDaoImpl extends MyBatisAbstractGenericDao<MeasuredHabitHistory, MeasuredHabitHistoryMapper, Long> implements com.javalad.habitdeveloper.dao.MeasuredHabitHistoryDao {

    public MeasuredHabitHistoryDaoImpl() {
        super(MeasuredHabitHistoryMapper.class);
    }
}
