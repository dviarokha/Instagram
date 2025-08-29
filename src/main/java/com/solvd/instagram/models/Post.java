package com.solvd.instagram.models;

import java.time.LocalDateTime;
import java.util.List;

public class Post {
    private Long id;
    private LocalDateTime postedAt;
    private Long userId;
    private Long postTypeId;
    private List<PostTag> postTags;
    private List<Comment> comments;
    private List<Like> likes;

    public Post() {
    }

    public Post(Long id, LocalDateTime postedAt, Long userId, Long postTypeId,  List<PostTag> postTags,
                List<Comment> comments, List<Like> likes) {
        this.id = id;
        this.postedAt = postedAt;
        this.userId = userId;
        this.postTypeId = postTypeId;
        this.postTags = postTags;
        this.comments = comments;
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostTypeId() {
        return postTypeId;
    }

    public void setPostTypeId(Long postTypeId) {
        this.postTypeId = postTypeId;
    }

    public List<PostTag> getPostTags() {
        return postTags;
    }

    public void setPostTags(List<PostTag> postTags) {
        this.postTags = postTags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}
