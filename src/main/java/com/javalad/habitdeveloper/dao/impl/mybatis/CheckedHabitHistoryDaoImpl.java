package com.javalad.habitdeveloper.dao.impl.mybatis;

import com.javalad.habitdeveloper.dao.impl.mybatis.mapper.CheckedHabitHistoryMapper;
import com.javalad.habitdeveloper.domain.CheckedHabitHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author KotovDV
 */
@Repository("checkedHabitHistoryDao")
public class CheckedHabitHistoryDaoImpl extends MyBatisAbstractGenericDao<CheckedHabitHistory, CheckedHabitHistoryMapper, Long> implements com.javalad.habitdeveloper.dao.CheckedHabitHistoryDao {

    public CheckedHabitHistoryDaoImpl() {
        super(CheckedHabitHistoryMapper.class);
    }

    @Override
    public List<CheckedHabitHistory> getByHabitId(long checkedHabitId) {
        return getMapper().getByHabitId(checkedHabitId);
    }
}
