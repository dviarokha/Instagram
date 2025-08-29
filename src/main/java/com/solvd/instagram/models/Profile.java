package com.solvd.instagram.models;

public class Profile {
    private Long id;
    private boolean isVerified;
    private boolean isPrivate;
    private String profileName;


    public Profile() {
    }

    public Profile(Long id, boolean isVerified, boolean isPrivate, String profileName) {
        this.id = id;
        this.isVerified = isVerified;
        this.isPrivate = isPrivate;
        this.profileName = profileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}
