package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.util.List;

public interface ITagDAO<T> extends IBaseDAO<T> {
    List<T> getAllTags() throws SQLException;
    T findByName(String name) throws SQLException;
}
