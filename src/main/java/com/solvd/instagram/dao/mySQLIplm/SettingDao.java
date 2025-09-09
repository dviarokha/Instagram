package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.dao.ISettingDAO;
import com.solvd.instagram.models.Post;
import com.solvd.instagram.models.Setting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SettingDao extends MySQL implements ISettingDAO<Setting> {
    static Logger logger = LogManager.getLogger(SettingDao.class);

    @Override
    public List<Setting> getAllSetting() throws SQLException {
        List<Setting> settings = new ArrayList<>();
        String sql = "SELECT * FROM Settings";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    settings.add(resultsSetToSetting(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return settings;
    }

    @Override
    public Setting findIsDarkMode(boolean isDarkMode) throws SQLException {
        Setting setting = null;
        String sql = "SELECT * FROM Settings WHERE is_dark_mode = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setBoolean(1, isDarkMode);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    setting = resultsSetToSetting(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return setting;
    }

    @Override
    public Setting findPrivacyLevel(String privacyLevel) throws SQLException {
        Setting setting = null;
        String sql = "SELECT * FROM Settings WHERE privacy_level = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setString(1, privacyLevel);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    setting = resultsSetToSetting(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return setting;
    }

    @Override
    public Setting findIsEmailNotification(boolean isEmailNotifications) throws SQLException {
        Setting setting = null;
        String sql = "SELECT * FROM Settings WHERE is_email_notification = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setBoolean(1, isEmailNotifications);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    setting = resultsSetToSetting(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return setting;
    }

    @Override
    public Setting findPushNotification(boolean isPushNotifications) throws SQLException {
        Setting setting = null;
        String sql = "SELECT * FROM Settings WHERE is_push_notification = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setBoolean(1, isPushNotifications);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    setting = resultsSetToSetting(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return setting;
    }

    @Override
    public Setting insert(Setting entity) throws SQLException {
        String sql = "INSERT INTO Settings(is_dark_mode, privacy_level, email_notification, push_notification) VALUES (?, ?, ?, ?)";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setBoolean(1, entity.isDarkMode());
            stmt.setString(2, entity.getPrivacyLevel());
            stmt.setBoolean(3, entity.isEmailNotification());
            stmt.setBoolean(4, entity.isPushNotification());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted == 0) {
                throw new SQLException("Insert failed");
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public Setting getById(Long id) throws SQLException {
        Setting setting = null;
        String sql = "SELECT * FROM Settings WHERE setting_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    setting = resultsSetToSetting(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return setting;
    }

    @Override
    public Setting update(Setting entity) throws SQLException {
        String sql = "UPDATE Settings SET is_dark_mode = ?, privacy_level = ?, email_notification = ?,push_notification = ? WHERE setting_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setBoolean(1, entity.isDarkMode());
            stmt.setString(2, entity.getPrivacyLevel());
            stmt.setBoolean(3, entity.isEmailNotification());
            stmt.setBoolean(4, entity.isPushNotification());
            stmt.setLong(5, entity.getId());
            int rowUpdated = stmt.executeUpdate();
            if (rowUpdated == 0) {
                throw new SQLException("Update failed");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        String sql = "DELETE FROM Settings WHERE setting_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement("DELETE FROM Settings WHERE setting_id = ?");
        ) {
            stmt.setLong(1, id);
            int rowDeleted = stmt.executeUpdate();
            if (rowDeleted == 0) {
                throw new SQLException("Delete failed");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

    }

    private Setting resultsSetToSetting(ResultSet rs) throws SQLException {
        Setting setting = new Setting();
        setting.setId(rs.getLong("setting_id"));
        setting.setIsDarkMode(rs.getBoolean("is_dark_mode"));
        setting.setPrivacyLevel(rs.getString("privacy_level"));
        setting.setEmailNotification(rs.getBoolean("email_notification"));
        setting.setPushNotification(rs.getBoolean("push_notification"));
        return setting;
    }


}
