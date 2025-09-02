package com.solvd.instagram.models;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private LocalDateTime commentedAt;
    private String textComment;
    private Long userId;
    private Long postId;

    public Comment(Long id, LocalDateTime commentedAt, String textComment, Long userId, Long postId) {
        this.id = id;
        this.commentedAt = commentedAt;
        this.textComment = textComment;
        this.userId = userId;
        this.postId = postId;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(LocalDateTime commentedAt) {
        this.commentedAt = commentedAt;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
