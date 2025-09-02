package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.util.List;

public interface IFollowDAO<T> extends IBaseDAO<T> {
    List<T> getAllFollowers() throws SQLException;
    T getFollowsByFollowerID(long id) throws SQLException;
    T getFollowsByFollowedID(long id) throws SQLException;
}
