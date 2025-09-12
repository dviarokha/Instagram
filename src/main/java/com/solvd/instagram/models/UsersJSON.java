package com.solvd.instagram.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UsersJSON {
    @JsonProperty("user")
    private List<User> users;

    public UsersJSON(List<User> users) {
        this.users = users;
    }

    public UsersJSON() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
