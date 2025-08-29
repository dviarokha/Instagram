package com.solvd.instagram.models;

import java.time.LocalDateTime;

public class Message {
    private Long id;
    private LocalDateTime sendAt;
    private boolean isRead;
    private String textMessage;
    private Long senderId;
    private Long receiverId;

    public Message() {
    }

    public Message(Long id, LocalDateTime sendAt, boolean isRead, String textMessage, Long senderId, Long receiverId) {
        this.id = id;
        this.sendAt = sendAt;
        this.isRead = isRead;
        this.textMessage = textMessage;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSendAt() {
        return sendAt;
    }

    public void setSendAt(LocalDateTime sendAt) {
        this.sendAt = sendAt;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}
