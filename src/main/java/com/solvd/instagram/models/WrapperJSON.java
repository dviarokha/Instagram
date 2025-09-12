package com.solvd.instagram.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WrapperJSON {
    @JsonProperty("users")
    private UsersJSON user;

    public UsersJSON getUser() {
        return user;
    }

    public void setUser(UsersJSON user) {
        this.user = user;
    }
}
