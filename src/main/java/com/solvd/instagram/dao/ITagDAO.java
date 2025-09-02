package com.solvd.instagram.dao;

import com.solvd.instagram.models.Tag;

import java.sql.SQLException;
import java.util.List;

public interface ITagDAO<T> extends IBaseDAO<T> {
    List<T> getAllTags() throws SQLException;
    T getTagByName(String name) throws SQLException;
}
