package com.solvd.instagram.models;

public class PostTag {
    private Long id;
    private Long tagId;
    private Long postId;

    public PostTag(Long id, Long tagId, Long postId) {
        this.id = id;
        this.tagId = tagId;
        this.postId = postId;
    }

    public PostTag() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
