package com.solvd.instagram.mybatis.myBatisIMPL;

import com.solvd.instagram.models.Setting;
import com.solvd.instagram.mybatis.MybatisSessionHolder;
import com.solvd.instagram.mybatis.SettingsMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SettingsMapperImpl implements SettingsMapper {

    @Override
    public void insert(Setting setting) {
        try(SqlSession session = MybatisSessionHolder.getSqlSession()) {
            SettingsMapper settingsMapper = session.getMapper(SettingsMapper.class);
            settingsMapper.insert(setting);
        }
    }

    @Override
    public List<Setting> getSettingsById(Long userId) {
        try(SqlSession session = MybatisSessionHolder.getSqlSession()) {
            SettingsMapper settingsMapper = session.getMapper(SettingsMapper.class);
            return settingsMapper.getSettingsById(userId);
        }
    }

    @Override
    public void updateNotificationSettings(Long userId, boolean emailNotification, boolean pushNotification) {
        try(SqlSession session = MybatisSessionHolder.getSqlSession()) {
            SettingsMapper settingsMapper = session.getMapper(SettingsMapper.class);
            settingsMapper.updateNotificationSettings(userId, emailNotification, pushNotification);
        }
    }
}
