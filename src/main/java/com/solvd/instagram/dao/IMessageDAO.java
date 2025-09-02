package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface IMessageDAO<T> extends IBaseDAO<T> {
    List<T> getAllMessages() throws SQLException;
    T getMessagesBySendAt(LocalDateTime sendAt) throws SQLException;
    T getMessagesByTextMessage(String textMessage) throws SQLException;
    T getMessagesBySenderId(long senderId) throws SQLException;
    T getMessagesByReceiverId(long receiverId) throws SQLException;
}
