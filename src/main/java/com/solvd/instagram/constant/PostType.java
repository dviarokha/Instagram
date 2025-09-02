package com.solvd.instagram.constant;

public enum PostType {
    IMAGE(1L, "IMAGE"),
    VIDEO(2L, "VIDEO"),
    CAROUSEL(3L, "CAROUSEL"),
    REELS(4L, "REELS"),
    LIVE(5L, "LIVE"),
    IGTV(6L, "IGTV"),
    SHOP(7L, "SHOP"),;

    private final Long id;
    private final String typeName;

    PostType(Long id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public Long getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }
}
