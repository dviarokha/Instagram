package com.solvd.instagram.models;

public class UserType {
    private Long id;
    private String typeName;

    public UserType() {
    }

    public UserType(Long id, String typeName) {
        this.id = id;
        this.typeName = typeName;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

}
