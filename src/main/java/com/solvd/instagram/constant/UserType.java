package com.solvd.instagram.constant;

public enum UserType {
    PERSONAL(1L,  "PERSONAL"),
    CREATOR(2L,  "CREATOR"),
    BUSINESS(3L,  "BUSINESS");

    private final Long id;
    private final String typeName;


    UserType(Long id, String typeName) {
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
