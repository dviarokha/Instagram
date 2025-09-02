package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.bd.MySQL;
import com.solvd.instagram.dao.ISettingDAO;
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
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Setting> settings = new ArrayList<>();
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Settings ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Setting setting = new Setting();
                setting.setId(rs.getLong("id"));
                setting.setIsDarkMode(rs.getBoolean("isDarkMode"));
                setting.setPrivacyLevel(rs.getString("privacyLevel"));
                setting.setEmailNotification(rs.getBoolean("emailNotification"));
                setting.setPushNotification(rs.getBoolean("pushNotification"));
                settings.add(setting);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }  finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return settings;
    }

    @Override
    public Setting getSettingIsDarkMode(boolean isDarkMode) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Setting setting = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Settings WHERE is_dark_mode = ?");
            stmt.setBoolean(1, isDarkMode);
            rs = stmt.executeQuery();
            while (rs.next()) {
                setting = new Setting();
                setting.setId(rs.getLong("id"));
                setting.setIsDarkMode(rs.getBoolean("is_dark_mode"));
                setting.setPrivacyLevel(rs.getString("privacy_level"));
                setting.setEmailNotification(rs.getBoolean("email_notification"));
                setting.setPushNotification(rs.getBoolean("push_notification"));
                setting.setUserId(rs.getLong("user_id"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }   finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return setting;
    }

    @Override
    public Setting getSettingPrivacyLevel(String privacyLevel) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Setting setting = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Settings WHERE privacy_level = ?");
            stmt.setString(1, privacyLevel);
            rs = stmt.executeQuery();
            while (rs.next()) {
                setting = new Setting();
                setting.setId(rs.getLong("id"));
                setting.setIsDarkMode(rs.getBoolean("is_dark_mode"));
                setting.setPrivacyLevel(rs.getString("privacy_level"));
                setting.setEmailNotification(rs.getBoolean("email_notification"));
                setting.setPushNotification(rs.getBoolean("push_notification"));
                setting.setUserId(rs.getLong("user_id"));
            }
        }  catch (Exception e) {
            logger.error(e.getMessage());
        }   finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return setting;
    }

    @Override
    public Setting getSettingIsEmailNotifications(boolean isEmailNotifications) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Setting setting = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Settings WHERE email_notification = ?");
            stmt.setBoolean(1, isEmailNotifications);
            rs = stmt.executeQuery();
            while (rs.next()) {
                setting = new Setting();
                setting.setId(rs.getLong("id"));
                setting.setIsDarkMode(rs.getBoolean("is_dark_mode"));
                setting.setPrivacyLevel(rs.getString("privacy_level"));
                setting.setEmailNotification(rs.getBoolean("email_notification"));
                setting.setPushNotification(rs.getBoolean("push_notification"));
                setting.setUserId(rs.getLong("user_id"));
            }
        }  catch (Exception e) {
            logger.error(e.getMessage());
        }   finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return setting;
    }

    @Override
    public Setting getSettingPushNotifications(boolean isPushNotifications) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Setting setting = null;
            try {
                c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                stmt = c.prepareStatement("SELECT * FROM Settings WHERE push_notification = ?");
                stmt.setBoolean(1, isPushNotifications);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    setting = new Setting();
                    setting.setId(rs.getLong("id"));
                    setting.setIsDarkMode(rs.getBoolean("is_dark_mode"));
                    setting.setPrivacyLevel(rs.getString("privacy_level"));
                    setting.setEmailNotification(rs.getBoolean("email_notification"));
                    setting.setPushNotification(rs.getBoolean("push_notification"));
                    setting.setUserId(rs.getLong("user_id"));
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (c != null) {
                    c.close();
                }
            }
        return setting;
    }

    @Override
    public Setting insert(Setting entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("INSERT INTO Settings(is_dark_mode, privacy_level, email_notification, push_notification) " +
                    "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setBoolean(1, entity.isDarkMode());
            stmt.setString(2, entity.getPrivacyLevel());
            stmt.setBoolean(3, entity.isEmailNotification());
            stmt.setBoolean(4, entity.isPushNotification());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        }   catch (Exception e) {
            logger.error(e.getMessage());
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return entity;
    }

    @Override
    public Setting getById(Long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Setting setting = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Settings WHERE setting_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                setting = new Setting();
                setting.setId(rs.getLong("id"));
                setting.setIsDarkMode(rs.getBoolean("is_dark_mode"));
                setting.setPrivacyLevel(rs.getString("privacy_level"));
                setting.setEmailNotification(rs.getBoolean("email_notification"));
                setting.setPushNotification(rs.getBoolean("push_notification"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }  finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return setting;
    }

    @Override
    public Setting update(Setting entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("UPDATE Settings SET is_dark_mode = ?, privacy_level = ?, email_notification = ?, " +
                    "push_notification = ? WHERE setting_id = ?");
            stmt.setBoolean(1, entity.isDarkMode());
            stmt.setString(2, entity.getPrivacyLevel());
            stmt.setBoolean(3, entity.isEmailNotification());
            stmt.setBoolean(4, entity.isPushNotification());
            stmt.setLong(5, entity.getId());
            stmt.executeUpdate();
        }   catch (SQLException e) {
            logger.error(e.getMessage());
        }   finally {
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("DELETE FROM Settings WHERE setting_id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }   finally {
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }

    }
}
