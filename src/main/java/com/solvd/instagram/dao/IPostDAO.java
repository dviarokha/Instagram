package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface IPostDAO<T> extends IBaseDAO<T> {
    List<T> getAllPosts() throws SQLException;
    T getPostByPostedAt(LocalDateTime postedAt)  throws SQLException;
    T getPostByPostTypeId(long postTypeId) throws SQLException;
    T getPostByUserId(long userId) throws SQLException;
}
