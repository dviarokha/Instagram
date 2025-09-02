package com.solvd.instagram.dao.mySQLIplm;


import com.solvd.instagram.bd.MySQL;
import com.solvd.instagram.dao.IMessageDAO;

import com.solvd.instagram.models.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageDAO extends MySQL implements IMessageDAO<Message> {
    private static final Logger LOGGER = LogManager.getLogger(MessageDAO.class);

    @Override
    public List<Message> getAllMessages() throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Message> messages = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Messages");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getLong("id"));
                message.setSendAt(LocalDateTime.parse(rs.getString("send_at")));
                message.setRead(rs.getBoolean("is_read"));
                message.setTextMessage(rs.getString("text_message"));
                message.setSenderId(rs.getLong("sender_id"));
                message.setReceiverId(rs.getLong("receiver_id"));
                messages.add(message);
            }
        }  catch (Exception e) {
            LOGGER.error(e.getMessage());
        }  finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return messages;
    }

    @Override
    public Message getMessagesBySendAt(LocalDateTime sendAt) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Message message = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Messages WHERE send_at = ?");
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            rs = stmt.executeQuery();
            while (rs.next()) {
                message = new Message();
                message.setId(rs.getLong("id"));
                message.setSendAt(LocalDateTime.parse(rs.getString("send_at")));
                message.setRead(rs.getBoolean("is_read"));
                message.setTextMessage(rs.getString("text_message"));
                message.setSenderId(rs.getLong("sender_id"));
                message.setReceiverId(rs.getLong("receiver_id"));
            }
        }    catch (Exception e) {
            LOGGER.error(e.getMessage());
        }   finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return message;
    }

    @Override
    public Message getMessagesByTextMessage(String textMessage) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Message message = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Messages WHERE text_message = ?");
            stmt.setString(1, textMessage);
            rs = stmt.executeQuery();
            while (rs.next()) {
                message = new Message();
                message.setId(rs.getLong("id"));
                message.setSendAt(LocalDateTime.parse(rs.getString("send_at")));
                message.setRead(rs.getBoolean("is_read"));
                message.setTextMessage(rs.getString("text_message"));
                message.setSenderId(rs.getLong("sender_id"));
                message.setReceiverId(rs.getLong("receiver_id"));
            }
        }   catch (Exception e) {
            LOGGER.error(e.getMessage());
        }   finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return message;
    }

    @Override
    public Message getMessagesBySenderId(long senderId) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Message message = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Messages WHERE sender_id = ?");
            stmt.setLong(1, senderId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                message = new Message();
                message.setId(rs.getLong("id"));
                message.setSendAt(LocalDateTime.parse(rs.getString("send_at")));
                message.setRead(rs.getBoolean("is_read"));
                message.setTextMessage(rs.getString("text_message"));
                message.setSenderId(rs.getLong("sender_id"));
            }
        }    catch (Exception e) {
            LOGGER.error(e.getMessage());
        }   finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return message;
    }

    @Override
    public Message getMessagesByReceiverId(long receiverId) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Message message = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Messages WHERE receiver_id = ?");
            stmt.setLong(1, receiverId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                message = new Message();
                message.setId(rs.getLong("id"));
                message.setSendAt(LocalDateTime.parse(rs.getString("send_at")));
                message.setRead(rs.getBoolean("is_read"));
                message.setTextMessage(rs.getString("text_message"));
                message.setSenderId(rs.getLong("sender_id"));
                message.setReceiverId(rs.getLong("receiver_id"));
            }
        }    catch (Exception e) {
            LOGGER.error(e.getMessage());
        }     finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return message;
    }

    @Override
    public Message insert(Message entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("INSERT INTO  Messages(send_at, is_read, text_message, sender_id, receiver_id) " +
                    "VALUES (?,?,?,?,?)",  Statement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setBoolean(2, entity.isRead());
            stmt.setString(3, entity.getTextMessage());
            stmt.setLong(4, entity.getSenderId());
            stmt.setLong(5, entity.getReceiverId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        }  catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        }  finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return entity;
    }

    @Override
    public Message getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Message message = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Messages WHERE message_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                message = new Message();
                message.setId(id);
                message.setSenderId(rs.getLong("sender_id"));
                message.setReceiverId(rs.getLong("receiver_id"));
                message.setTextMessage(rs.getString("text_message"));
                message.setRead(rs.getBoolean("is_read"));
                message.setSendAt(LocalDateTime.parse(rs.getString("send_at")));
            }
        }   catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }  finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return message;
    }

    @Override
    public Message update(Message entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("UPDATE Messages SET send_at = ?, is_read = ?,  text_message = ?, sender_id = ?, " +
                    "receiver_id = ? WHERE message_id = ?");
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setBoolean(2, entity.isRead());
            stmt.setString(3, entity.getTextMessage());
            stmt.setLong(4, entity.getSenderId());
            stmt.setLong(5, entity.getReceiverId());
            stmt.setLong(6, entity.getId());
            stmt.executeUpdate();
        }  catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }   finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("DELETE FROM Messages WHERE message_id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
