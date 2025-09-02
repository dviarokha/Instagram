package com.solvd.instagram.dao;

import java.sql.SQLException;

public interface IBaseDAO <T> {
    T insert(T entity) throws SQLException;
    T getById(Long id) throws SQLException;
    T update(T entity) throws SQLException;
    void removeById(Long id) throws SQLException;
}
