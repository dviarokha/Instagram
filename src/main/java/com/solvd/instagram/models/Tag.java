package com.solvd.instagram.models;

import java.util.List;

public class Tag {
    private Long id;
    private String tagName;
    private List<PostTag> postTags;

    public Tag() {
    }

    public Tag(Long id, String tagName, List<PostTag> postTags) {
        this.tagName = tagName;
        this.id = id;
        this.postTags = postTags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<PostTag> getPostTags() {
        return postTags;
    }

    public void setPostTags(List<PostTag> postTags) {
        this.postTags = postTags;
    }
}
