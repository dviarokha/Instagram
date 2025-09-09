package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.util.List;

public interface ISettingDAO<T> extends IBaseDAO<T> {
    List<T> getAllSetting() throws SQLException;
    T findIsDarkMode(boolean isDarkMode) throws SQLException;
    T findPrivacyLevel(String privacyLevel) throws SQLException;
    T findIsEmailNotification(boolean isEmailNotifications) throws SQLException;
    T findPushNotification(boolean isPushNotifications) throws SQLException;
}
