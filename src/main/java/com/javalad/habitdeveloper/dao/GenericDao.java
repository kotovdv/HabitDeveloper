package com.javalad.habitdeveloper.dao;

import java.util.List;

/**
 * @author KotovDV
 */
public interface GenericDao<T, PK> {

    void add(T entity);

    T get(PK id);

    void update(T entity);

    void delete(PK id);

    long count();

    List<T> getAll();

    boolean exists(PK id);
}
