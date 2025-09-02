package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.util.List;

public interface ISettingDAO<T> extends IBaseDAO<T> {
    List<T> getAllSetting() throws SQLException;
    T getSettingIsDarkMode(boolean isDarkMode) throws SQLException;
    T getSettingPrivacyLevel(String privacyLevel) throws SQLException;
    T getSettingIsEmailNotifications(boolean isEmailNotifications) throws SQLException;
    T getSettingPushNotifications(boolean isPushNotifications) throws SQLException;
}
