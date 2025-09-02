package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface INotificationDAO<T> extends IBaseDAO<T> {
    List<T> getAllNotifications() throws SQLException;
    T getNotificationByIsRead(boolean isRead) throws SQLException;
    T getNotiificationByNotifyedAt(LocalDateTime time) throws SQLException;
    T getNotificationByTextNotification(String textNotification) throws SQLException;
    T getNotificationByUserId(long id) throws SQLException;
}
