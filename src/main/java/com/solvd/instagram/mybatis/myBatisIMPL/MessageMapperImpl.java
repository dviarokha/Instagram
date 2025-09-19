package com.solvd.instagram.mybatis.myBatisIMPL;

import com.solvd.instagram.models.Message;
import com.solvd.instagram.mybatis.MessageMapper;
import com.solvd.instagram.mybatis.MybatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MessageMapperImpl implements MessageMapper {
    @Override
    public void updateMessage(Long id, String message) {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            MessageMapper messageMapper = session.getMapper(MessageMapper.class);
            messageMapper.updateMessage(id, message);
        }
    }

    @Override
    public void deleteMessageById(int id) {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            MessageMapper messageMapper = session.getMapper(MessageMapper.class);
            messageMapper.deleteMessageById(id);
        }
    }

    @Override
    public List<Message> getBySenderId(Long senderId) {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            MessageMapper messageMapper = session.getMapper(MessageMapper.class);
            return messageMapper.getBySenderId(senderId);
        }
    }

    @Override
    public void markAsRead(Long id) {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            MessageMapper messageMapper = session.getMapper(MessageMapper.class);
            messageMapper.markAsRead(id);
        }
    }
}
