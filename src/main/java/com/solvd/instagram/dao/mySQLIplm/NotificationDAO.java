package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.bd.MySQL;

import com.solvd.instagram.dao.INotificationDAO;
import com.solvd.instagram.models.Notification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO extends MySQL implements INotificationDAO<Notification> {
    private static final Logger LOGGER = LogManager.getLogger(NotificationDAO.class);

    @Override
    public List<Notification> getAllNotifications() throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Notification> notifications = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Notifications");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Notification notification = new Notification();
                notification.setId(rs.getLong("id"));
                notification.setRead(rs.getBoolean("is_read"));
                notification.setNotifiedAt(LocalDateTime.parse(rs.getString("notified_at")));
                notification.setTextNotification(rs.getString("text_notification"));
                notification.setUserId(rs.getLong("user_id"));
                notifications.add(notification);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
        return notifications;
    }

    @Override
    public Notification getNotificationByIsRead(boolean isRead) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Notification notification = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Notifications WHERE is_read = ?");
            stmt.setBoolean(1, isRead);
            rs = stmt.executeQuery();
            while (rs.next()) {
                notification = new Notification();
                notification.setId(rs.getLong("id"));
                notification.setRead(rs.getBoolean("is_read"));
                notification.setNotifiedAt(LocalDateTime.parse(rs.getString("notified_at")));
                notification.setTextNotification(rs.getString("text_notification"));
                notification.setUserId(rs.getLong("user_id"));
            }
        }  catch (Exception e) {
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
        return notification;
    }

    @Override
    public Notification getNotiificationByNotifyedAt(LocalDateTime time) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Notification notification = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Notifications WHERE notified_at = ?");
            stmt.setTimestamp(1, Timestamp.valueOf(time));
            rs = stmt.executeQuery();
            while (rs.next()) {
                notification = new Notification();
                notification.setId(rs.getLong("id"));
                notification.setRead(rs.getBoolean("is_read"));
                notification.setNotifiedAt(LocalDateTime.parse(rs.getString("notified_at")));
                notification.setTextNotification(rs.getString("text_notification"));
                notification.setUserId(rs.getLong("user_id"));
            }
        }   catch (Exception e) {
            LOGGER.error(e.getMessage());
        }  finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return notification;
    }

    @Override
    public Notification getNotificationByTextNotification(String textNotification) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Notification notification = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Notifications WHERE text_notification = ?");
            stmt.setString(1, textNotification);
            rs = stmt.executeQuery();
            while (rs.next()) {
                notification = new Notification();
                notification.setId(rs.getLong("id"));
                notification.setRead(rs.getBoolean("is_read"));
                notification.setNotifiedAt(LocalDateTime.parse(rs.getString("notified_at")));
                notification.setTextNotification(rs.getString("text_notification"));
                notification.setUserId(rs.getLong("user_id"));
            }
        }  catch (Exception e) {
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
        return notification;
    }

    @Override
    public Notification getNotificationByUserId(long id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Notification notification = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Notifications WHERE user_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                notification = new Notification();
                notification.setId(rs.getLong("id"));
                notification.setRead(rs.getBoolean("is_read"));
                notification.setNotifiedAt(LocalDateTime.parse(rs.getString("notified_at")));
                notification.setTextNotification(rs.getString("text_notification"));
                notification.setUserId(rs.getLong("user_id"));
            }
        }   catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
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

        return notification;
    }

    @Override
    public Notification insert(Notification entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("INSERT INTO Notifications(is_read, notifyed_at, text_notification, user_id) " +
                    "VALUES (?,?,?,?)",  Statement.RETURN_GENERATED_KEYS);
            stmt.setBoolean(1, entity.isRead());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(3, entity.getTextNotification());
            stmt.setLong(4, entity.getUserId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        }  catch (Exception e) {
            LOGGER.error(e.getMessage());
        }  finally {
            if (rs != null) {rs.close();}
            if (stmt != null) {stmt.close();}
            if (connection != null) {connection.close();}
        }
        return entity;
    }

    @Override
    public Notification getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Notification notification = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Notifications WHERE notification_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                notification = new Notification();
                notification.setId(rs.getLong(1));
                notification.setTextNotification(rs.getString(2));
                notification.setNotifiedAt(LocalDateTime.parse(rs.getString(3)));
                notification.setTextNotification(rs.getString(4));
                notification.setUserId(rs.getLong(5));
            }
        }   catch (Exception e) {
            LOGGER.error(e.getMessage());
        }   finally {
            if (rs != null) {rs.close();}
            if (stmt != null) {stmt.close();}
            if (connection != null) {connection.close();}
        }
        return notification;
    }

    @Override
    public Notification update(Notification entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("UPDATE Notifications SET is_read = ? , notifyed_at = ? , text_notification = ? WHERE notification_id = ?");
            stmt.setBoolean(1, entity.isRead());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(3, entity.getTextNotification());
            stmt.setLong(4, entity.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }   finally {
            if (stmt != null) {stmt.close();}
            if (connection != null) {connection.close();}
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("DELETE FROM Notifications WHERE notification_id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
