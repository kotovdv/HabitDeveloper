package com.javalad.habitdeveloper.dao.impl.mybatis;

import com.javalad.habitdeveloper.dao.GenericDao;
import com.javalad.habitdeveloper.dao.impl.mybatis.mapper.MyBatisGenericMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author KotovDV
 */
public abstract class MyBatisAbstractGenericDao<T, M extends MyBatisGenericMapper<T, PK>, PK> implements GenericDao<T, PK> {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private final Class<M> mapperClass;

    public MyBatisAbstractGenericDao(Class<M> mapperClass) {
        this.mapperClass = mapperClass;
    }

    @Override
    public T add(T entity) {
        getMapper().add(entity);
        return entity;
    }

    @Override
    public T get(PK id) {
        return getMapper().get(id);
    }

    @Override
    public void update(T entity) {
        getMapper().update(entity);
    }

    @Override
    public void delete(PK id) {
        getMapper().delete(id);
    }

    @Override
    public long count() {
        return getMapper().count();
    }

    @Override
    public List<T> getAll() {
        return getMapper().getAll();
    }

    @Override
    public boolean exists(PK id) {
        return getMapper().exists(id);
    }

    protected M getMapper() {
        return sqlSessionTemplate.getMapper(mapperClass);
    }

}
