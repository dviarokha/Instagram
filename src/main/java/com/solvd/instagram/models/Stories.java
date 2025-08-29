package com.solvd.instagram.models;

import java.time.LocalDateTime;

public class Stories {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private Long userId;

    public Stories() {
    }
    public Stories(Long id, LocalDateTime createdAt, LocalDateTime expiredAt, Long userId) {
        this.id = id;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
