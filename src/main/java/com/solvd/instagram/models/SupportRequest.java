package com.solvd.instagram.models;

import java.time.LocalDate;

public class SupportRequest {
    private Long id;
    private String requestName;
    private LocalDate requestDate;
    private Long userId;


    public SupportRequest(Long id, String requestName, LocalDate requestDate, Long userId) {
        this.id = id;
        this.requestName = requestName;
        this.requestDate = requestDate;
        this.userId = userId;
    }

    public SupportRequest() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
