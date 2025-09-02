package com.solvd.instagram.models;

public class Setting {
    private Long id;
    private boolean isDarkMode;
    private String privacyLevel;
    private boolean emailNotification;
    private boolean pushNotification;
    private Long userId;

    public Setting() {
    }

    public Setting(Long id, boolean isDarkMode, String privacyLevel, boolean emailNotification, boolean pushNotification,
                   Long userId) {
        this.id = id;
        this.isDarkMode = isDarkMode;
        this.privacyLevel = privacyLevel;
        this.emailNotification = emailNotification;
        this.pushNotification = pushNotification;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public void setIsDarkMode(boolean darkMode) {
        isDarkMode = darkMode;
    }

    public String getPrivacyLevel() {
        return privacyLevel;
    }

    public void setPrivacyLevel(String privacyLevel) {
        this.privacyLevel = privacyLevel;
    }

    public boolean isEmailNotification() {
        return emailNotification;
    }

    public void setEmailNotification(boolean emailNotification) {
        this.emailNotification = emailNotification;
    }

    public boolean isPushNotification() {
        return pushNotification;
    }

    public void setPushNotification(boolean pushNotification) {
        this.pushNotification = pushNotification;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
