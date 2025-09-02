package com.solvd.instagram.models;

import java.time.LocalDate;
import java.util.List;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String emailAddress;
    private String phoneNumber;
    private Long userTypeId;
    private Long profileId;
    private List<Post> posts;
    private List<Stories> stories;
    private List<Message> senderId;
    private List<Message> receiverId;
    private List<SupportRequest> supportRequests;
    private List<Comment> comments;
    private List<Like> likes;
    private List<Notification> notifications;
    private List<Follow> followerId;
    private List<Follow> followingId;



    public User(Long id, String firstName, String lastName, LocalDate dateOfBirth, String emailAddress, String phoneNumber,
                Long userTypeId, Long profileId, List<Post> posts, List<Stories> stories, List<Message> senderId,
                List<Message> receiverId, List<SupportRequest> supportRequests, List<Comment> comments, List<Like> likes,
                List<Notification> notifications, List<Follow> followerId, List<Follow> followingId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.userTypeId = userTypeId;
        this.profileId = profileId;
        this.posts = posts;
        this.stories = stories;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.supportRequests = supportRequests;
        this.comments = comments;
        this.likes = likes;
        this.notifications = notifications;
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public List<Message> getSenderId() {
        return senderId;
    }

    public void setSenderId(List<Message> senderId) {
        this.senderId = senderId;
    }

    public List<Message> getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(List<Message> receiverId) {
        this.receiverId = receiverId;
    }

    public List<SupportRequest> getSupportRequests() {
        return supportRequests;
    }

    public void setSupportRequests(List<SupportRequest> supportRequests) {
        this.supportRequests = supportRequests;
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

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Follow> getfollowerId() {
        return followerId;
    }

    public void setfollowerId(List<Follow> followerId) {
        this.followerId = followerId;
    }

    public List<Follow> getfollowingId() {
        return followingId;
    }

    public void setfollowingId(List<Follow> followingId) {
        this.followingId = followingId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", emailAddress='" + emailAddress + '\'' +
                ", userTypeId=" + userTypeId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profileId=" + profileId +
                '}';
    }
}
