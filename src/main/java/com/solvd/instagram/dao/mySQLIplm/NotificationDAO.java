package com.solvd.instagram.dao.mySQLIplm;

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
        List<Notification> notifications = new ArrayList<>();
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Notifications");
            ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Notification notification = resultSetToNotification(rs);
                    notifications.add(notification);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return notifications;
    }

    @Override
    public Notification getNotificationByIsRead(boolean isRead) throws SQLException {
        Notification notification = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Notifications WHERE is_read = ?");
        ) {
            stmt.setBoolean(1, isRead);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    notification = resultSetToNotification(rs);
                }
            }
        }  catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return notification;
    }

    @Override
    public Notification getNotiificationByNotifyedAt(LocalDateTime time) throws SQLException {
        Notification notification = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Notifications WHERE notified_at = ?");
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(time));
            try ( ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    notification = resultSetToNotification(rs);
                }
            }
        }   catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return notification;
    }

    @Override
    public Notification getNotificationByTextNotification(String textNotification) throws SQLException {
        Notification notification = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Notifications WHERE text_notification = ?");
        ) {
            stmt.setString(1, textNotification);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    notification = resultSetToNotification(rs);
                }
            }
        }  catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return notification;
    }

    @Override
    public Notification getNotificationByUserId(long id) throws SQLException {
        Notification notification = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Notifications WHERE user_id = ?");
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    notification = resultSetToNotification(rs);
                }
            }
        }   catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return notification;
    }

    @Override
    public Notification insert(Notification entity) throws SQLException {
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Notifications(is_read, notifyed_at, text_notification, user_id) " +
                    "VALUES (?,?,?,?)",  Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setBoolean(1, entity.isRead());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(3, entity.getTextNotification());
            stmt.setLong(4, entity.getUserId());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                while (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }
        }  catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public Notification getById(Long id) throws SQLException {
        Notification notification = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Notifications WHERE notification_id = ?");
        ) {
            stmt.setLong(1, id);
            try( ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    notification = resultSetToNotification(rs);
                }
            }
        }   catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return notification;
    }

    @Override
    public Notification update(Notification entity) throws SQLException {
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("UPDATE Notifications SET is_read = ? , notifyed_at = ? , text_notification = ? " +
                    "WHERE notification_id = ?");
        ) {
            stmt.setBoolean(1, entity.isRead());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(3, entity.getTextNotification());
            stmt.setLong(4, entity.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Notifications WHERE notification_id = ?");
        ) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private Notification resultSetToNotification(ResultSet rs) throws SQLException {
        Notification notification = new Notification();
        notification.setId(rs.getLong("id"));
        notification.setRead(rs.getBoolean("is_read"));
        notification.setTextNotification(rs.getString("text_notification"));
        notification.setNotifiedAt(LocalDateTime.parse(rs.getString("notified_at")));
        notification.setUserId(rs.getLong("user_id"));
        return notification;

    }
}
