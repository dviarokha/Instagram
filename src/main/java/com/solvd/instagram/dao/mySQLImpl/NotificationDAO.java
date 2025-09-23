package com.solvd.instagram.dao.mySQLImpl;

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
        String sql = "SELECT * FROM Notifications";
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement(sql);
            ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    notifications.add(resultSetToNotification(rs));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return notifications;
    }

    @Override
    public Notification findByIsRead(boolean isRead) throws SQLException {
        Notification notification = null;
        String sql = "SELECT * FROM Notifications WHERE is_read = ?";
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement(sql);
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
    public Notification findByNotifyedAt(LocalDateTime time) throws SQLException {
        Notification notification = null;
        String sql = "SELECT * FROM Notifications WHERE notifyed_at = ?";
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement(sql);
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
    public Notification findByTextNotification(String textNotification) throws SQLException {
        Notification notification = null;
        String sql = "SELECT * FROM Notifications WHERE text_notification = ?";
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement(sql);
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
    public Notification findByUserId(long id) throws SQLException {
        Notification notification = null;
        String sql = "SELECT * FROM Notifications WHERE user_id = ?";
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
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
        String sql = "INSERT INTO Notifications(is_read, notifyed_at, text_notification, user_id) VALUES (?,?,?,?)";
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setBoolean(1, entity.isRead());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(3, entity.getTextNotification());
            stmt.setLong(4, entity.getUserId());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Failed to insert notification");
            }
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
        String sql = "SELECT * FROM Notifications WHERE notification_id = ?";
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
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
        String sql = "UPDATE Notifications SET is_read = ? , notifyed_at = ? , text_notification = ? WHERE notification_id = ?";
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setBoolean(1, entity.isRead());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(3, entity.getTextNotification());
            stmt.setLong(4, entity.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Failed to update notification");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        String sql = "DELETE FROM Notifications WHERE notification_id = ?";
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Failed to delete notification");
            }
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
