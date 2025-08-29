package com.solvd.instagram.models;

public class Follow {
    private Long id;
    private Long followerId;
    private Long followedId;

    public Follow() {
    }

    public Follow(Long id, Long followerId, Long followedId) {
        this.id = id;
        this.followerId = followerId;
        this.followedId = followedId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Long followedId) {
        this.followedId = followedId;
    }
}
