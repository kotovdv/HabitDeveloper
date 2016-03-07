package com.javalad.habitdeveloper.dao.impl;

import com.javalad.habitdeveloper.dao.GenericDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author KotovDV
 */
public abstract class AbstractGenericDao<T, M extends GenericDao<T, PK>, PK> implements GenericDao<T, PK> {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private final Class<M> mapperClass;


    public AbstractGenericDao(Class<M> mapperClass) {
        this.mapperClass = mapperClass;
    }

    @Override
    public void add(T entity) {
        getMapper().add(entity);
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
    public void deleteAll() {
        getMapper().deleteAll();
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
