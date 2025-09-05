package com.solvd.instagram.dao.mySQLIplm;


import com.solvd.instagram.dao.IMessageDAO;

import com.solvd.instagram.models.Message;
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
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement  stmt = connection.prepareStatement("SELECT * FROM Messages");
            ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Message message = resultSetToMessage(rs);
                    messages.add(message);
                }
            }
        }  catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return messages;
    }

    @Override
    public Message getMessagesBySendAt(LocalDateTime sendAt) throws SQLException {
        Message message = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Messages WHERE send_at = ?");
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    message = resultSetToMessage(rs);
                }
            }
        }    catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return message;
    }

    @Override
    public Message getMessagesByTextMessage(String textMessage) throws SQLException {
        Message message = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Messages WHERE text_message = ?");
            ) {
            stmt.setString(1, textMessage);
            try(ResultSet  rs = stmt.executeQuery()) {
                while (rs.next()) {
                    message = resultSetToMessage(rs);
                }
            }
        }   catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return message;
    }

    @Override
    public Message getMessagesBySenderId(long senderId) throws SQLException {
        Message message = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Messages WHERE sender_id = ?");
        ) {
            stmt.setLong(1, senderId);
        try(ResultSet rs = stmt.executeQuery() ) {
            while (rs.next()) {
                message = resultSetToMessage(rs);
            }
        }
        }    catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return message;
    }

    @Override
    public Message getMessagesByReceiverId(long receiverId) throws SQLException {
        Message message = null;
        try (
            Connection  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Messages WHERE receiver_id = ?");
        ) {
            stmt.setLong(1, receiverId);
            try (ResultSet rs = stmt.executeQuery() ) {

            while (rs.next()) {
                message = resultSetToMessage(rs);
            }
            }
        }    catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return message;
    }

    @Override
    public Message insert(Message entity) throws SQLException {
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO  Messages(send_at, is_read, text_message, sender_id, receiver_id) " +
                    "VALUES (?,?,?,?,?)",  Statement.RETURN_GENERATED_KEYS);
            ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setBoolean(2, entity.isRead());
            stmt.setString(3, entity.getTextMessage());
            stmt.setLong(4, entity.getSenderId());
            stmt.setLong(5, entity.getReceiverId());
            stmt.executeUpdate();
            try(ResultSet rs = stmt.getGeneratedKeys()) {
                while (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }
        }  catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return entity;
    }

    @Override
    public Message getById(Long id) throws SQLException {
        Message message = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Messages WHERE message_id = ?");
        ) {
            stmt.setLong(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    message = resultSetToMessage(rs);
                }
            }
        }   catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return message;
    }

    @Override
    public Message update(Message entity) throws SQLException {
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("UPDATE Messages SET send_at = ?, is_read = ?,  text_message = ?, sender_id = ?, " +
                    "receiver_id = ? WHERE message_id = ?");
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setBoolean(2, entity.isRead());
            stmt.setString(3, entity.getTextMessage());
            stmt.setLong(4, entity.getSenderId());
            stmt.setLong(5, entity.getReceiverId());
            stmt.setLong(6, entity.getId());
            stmt.executeUpdate();
        }  catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Messages WHERE message_id = ?");
        ) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
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
