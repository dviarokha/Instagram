package com.solvd.instagram.mybatis;

import com.solvd.instagram.models.Setting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SettingsMapper {
    void insert(Setting setting);
    List<Setting> getSettingsById(@Param("userId") Long userId);
    void updateNotificationSettings(@Param("userId") Long userId, @Param("emailNotification") boolean emailNotification,
                                    @Param("pushNotification") boolean pushNotification);
}
