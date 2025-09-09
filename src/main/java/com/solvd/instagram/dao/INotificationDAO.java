package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface INotificationDAO<T> extends IBaseDAO<T> {
    List<T> getAllNotifications() throws SQLException;
    T findByIsRead(boolean isRead) throws SQLException;
    T findByNotifyedAt(LocalDateTime time) throws SQLException;
    T findByTextNotification(String textNotification) throws SQLException;
    T findByUserId(long id) throws SQLException;
}
