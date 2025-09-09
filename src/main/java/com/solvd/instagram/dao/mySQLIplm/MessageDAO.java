package com.solvd.instagram.dao.mySQLIplm;


import com.solvd.instagram.dao.IMessageDAO;

import com.solvd.instagram.models.Message;
import com.solvd.instagram.models.Post;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO extends MySQL implements IMessageDAO<Message> {
    private static final Logger LOGGER = LogManager.getLogger(MessageDAO.class);

    @Override
    public List<Message> getAllMessages() throws SQLException {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM Messages";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    messages.add(resultSetToMessage(rs));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return messages;
    }

    @Override
    public Message findBySendAt(LocalDateTime sendAt) throws SQLException {
        Message message = null;
        String sql = "SELECT * FROM Messages WHERE send_at = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    message = resultSetToMessage(rs);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return message;
    }

    @Override
    public Message findByTextMessage(String textMessage) throws SQLException {
        Message message = null;
        String sql = "SELECT * FROM Messages WHERE text_message = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setString(1, textMessage);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    message = resultSetToMessage(rs);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return message;
    }

    @Override
    public Message findBySenderId(long senderId) throws SQLException {
        Message message = null;
        String sql = "SELECT * FROM Messages WHERE sender_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, senderId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    message = resultSetToMessage(rs);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return message;
    }

    @Override
    public Message findByReceiverId(long receiverId) throws SQLException {
        Message message = null;
        String sql = "SELECT * FROM Messages WHERE receiver_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, receiverId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    message = resultSetToMessage(rs);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return message;
    }

    @Override
    public Message insert(Message entity) throws SQLException {
        String sql = "INSERT INTO  Messages(send_at, is_read, text_message, sender_id, receiver_id) VALUES (?,?,?,?,?)";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setBoolean(2, entity.isRead());
            stmt.setString(3, entity.getTextMessage());
            stmt.setLong(4, entity.getSenderId());
            stmt.setLong(5, entity.getReceiverId());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Insert failed");
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                while (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return entity;
    }

    @Override
    public Message getById(Long id) throws SQLException {
        Message message = null;
        String sql = "SELECT * FROM Messages WHERE message_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    message = resultSetToMessage(rs);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return message;
    }

    @Override
    public Message update(Message entity) throws SQLException {
        String sql = "UPDATE Messages SET send_at = ?, is_read = ?,  text_message = ?, sender_id = ?, receiver_id = ? WHERE message_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setBoolean(2, entity.isRead());
            stmt.setString(3, entity.getTextMessage());
            stmt.setLong(4, entity.getSenderId());
            stmt.setLong(5, entity.getReceiverId());
            stmt.setLong(6, entity.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Update failed");
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        String sql = "DELETE FROM Messages WHERE message_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
           int rowsDeleted = stmt.executeUpdate();
           if (rowsDeleted == 0) {
               throw new SQLException("Delete failed");
           }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private Message resultSetToMessage(ResultSet rs) throws SQLException {
        Message message = new Message();
        message.setId(rs.getLong("message_id"));
        message.setSenderId(rs.getLong("sender_id"));
        message.setReceiverId(rs.getLong("receiver_id"));
        message.setTextMessage(rs.getString("text_message"));
        message.setRead(rs.getBoolean("is_read"));
        message.setSendAt(LocalDateTime.parse(rs.getString("send_at")));
        return message;
    }
}
