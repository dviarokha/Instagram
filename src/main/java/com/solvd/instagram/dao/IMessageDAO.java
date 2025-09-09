package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface IMessageDAO<T> extends IBaseDAO<T> {
    List<T> getAllMessages() throws SQLException;
    T findBySendAt(LocalDateTime sendAt) throws SQLException;
    T findByTextMessage(String textMessage) throws SQLException;
    T findBySenderId(long senderId) throws SQLException;
    T findByReceiverId(long receiverId) throws SQLException;
}
