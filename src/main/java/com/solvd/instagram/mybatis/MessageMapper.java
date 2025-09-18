package com.solvd.instagram.mybatis;

import com.solvd.instagram.models.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    void updateMessage(@Param("id") Long id, @Param("message") String message);
    void deleteMessageById(@Param("id") int id);
    List<Message> getBySenderId(@Param("senderId") Long senderId);
    void markAsRead(@Param("id") Long id);
}
