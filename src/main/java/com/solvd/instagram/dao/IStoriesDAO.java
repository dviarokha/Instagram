package com.solvd.instagram.dao;

import com.solvd.instagram.models.Stories;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface IStoriesDAO<T> extends IBaseDAO<T> {
    List<T> getAllStories() throws SQLException;
    T findByCreatedDate(LocalDateTime createdDate) throws SQLException;
    T findByExpiredDate(LocalDateTime expiredDate) throws SQLException;
    Stories findByUserId(Long userId) throws SQLException;
}
