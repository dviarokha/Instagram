package com.solvd.instagram.models;

import java.time.LocalTime;

public class Like {
    private Long id;
    private LocalTime likedAt;
    private Long postId;
    private Long userId;


    public Like(Long id, LocalTime likedAt, Long postId, Long userId) {
        this.id = id;
        this.likedAt = likedAt;
        this.postId = postId;
        this.userId = userId;
    }
    public Like() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(LocalTime likedAt) {
        this.likedAt = likedAt;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
