package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface IStoriesDAO<T> extends IBaseDAO<T> {
    List<T> getAllStories() throws SQLException;
    T getStoriesByCreatedDate(LocalDateTime createdDate) throws SQLException;
    T getStoriesByExpiredDate(LocalDateTime expiredDate) throws SQLException;
    List<T> getAllStoriesByUserId(Long userId) throws SQLException;
}
