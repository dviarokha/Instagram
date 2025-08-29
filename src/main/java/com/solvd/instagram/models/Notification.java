package com.solvd.instagram.models;

import java.time.LocalDateTime;

public class Notification {
    private Long id;
    private boolean isRead;
    private LocalDateTime notifiedAt;
    private String textNotification;
    private Long userId;

    public Notification() {
    }

    public Notification(Long id, boolean isRead, LocalDateTime notifiedAt, String textNotification, Long userId) {
        this.id = id;
        this.isRead = isRead;
        this.notifiedAt = notifiedAt;
        this.textNotification = textNotification;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public LocalDateTime getNotifiedAt() {
        return notifiedAt;
    }

    public void setNotifiedAt(LocalDateTime notifiedAt) {
        this.notifiedAt = notifiedAt;
    }

    public String getTextNotification() {
        return textNotification;
    }

    public void setTextNotification(String textNotification) {
        this.textNotification = textNotification;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
