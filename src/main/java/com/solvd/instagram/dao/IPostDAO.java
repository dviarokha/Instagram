package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface IPostDAO<T> extends IBaseDAO<T> {
    List<T> getAllPosts() throws SQLException;
    T findByPostedAt(LocalDateTime postedAt)  throws SQLException;
    List<T> findByPostTypeId(long postTypeId) throws SQLException;
    List<T> findByUserId(long userId) throws SQLException;
}
