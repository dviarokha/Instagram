package com.solvd.instagram.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.solvd.instagram.adapter.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;


@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @XmlAttribute
    @JsonProperty("id")
    private Long id;
    @XmlElement(name = "firstName")
    @JsonProperty("firstName")
    private String firstName;
    @XmlElement(name = "lastName")
    @JsonProperty("lastName")
    private String lastName;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @XmlElement(name = "emailAddress")
    @JsonProperty("emailAddress")
    private String emailAddress;
    @XmlElement(name = "phoneNumber")
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @XmlElement(name = "userTypeId")
    @JsonProperty("userTypeId")
    private Long userTypeId;
    @XmlElement(name = "profileId")
    @JsonProperty("profileId")
    private Long profileId;


    public User(Long id, String firstName, String lastName, LocalDate dateOfBirth, String emailAddress, String phoneNumber,
                Long userTypeId, Long profileId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.userTypeId = userTypeId;
        this.profileId = profileId;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }
    @JsonSetter("id")
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    @JsonSetter("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    @JsonSetter("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    @JsonSetter("dateOfBirth")
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress(String mail) {
        return emailAddress;
    }
    @JsonSetter("emailAddress")
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    @JsonSetter("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getUserTypeId() {
        return userTypeId;
    }
    @JsonSetter("userTypeId")
    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Long getProfileId() {
        return profileId;
    }
    @JsonSetter("profileId")
    public void setProfileId(Long profileId) {
        this.profileId = profileId;
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
