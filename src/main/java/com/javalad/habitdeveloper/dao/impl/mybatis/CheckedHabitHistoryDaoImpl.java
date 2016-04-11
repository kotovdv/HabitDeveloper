package com.javalad.habitdeveloper.dao.impl.mybatis;

import com.javalad.habitdeveloper.dao.CheckedHabitHistoryDao;
import com.javalad.habitdeveloper.dao.impl.mybatis.mapper.CheckedHabitHistoryMapper;
import com.javalad.habitdeveloper.domain.CheckedHabitHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author KotovDV
 */
@Repository
public class CheckedHabitHistoryDaoImpl extends MyBatisAbstractGenericDao<CheckedHabitHistory, CheckedHabitHistoryMapper, Long> implements CheckedHabitHistoryDao {

    public CheckedHabitHistoryDaoImpl() {
        super(CheckedHabitHistoryMapper.class);
    }

    @Override
    public List<CheckedHabitHistory> getByHabitId(long checkedHabitId) {
        return getMapper().getByHabitId(checkedHabitId);
    }
}
