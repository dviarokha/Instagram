package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.util.List;

public interface IFollowDAO<T> extends IBaseDAO<T> {
    List<T> getAllFollowers() throws SQLException;
    T findByFollowerID(long id) throws SQLException;
    T findByFollowedID(long id) throws SQLException;
}
