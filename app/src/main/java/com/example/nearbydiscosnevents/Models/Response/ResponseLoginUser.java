package com.example.nearbydiscosnevents.Models.Response;

import com.example.nearbydiscosnevents.Models.User;

import java.util.List;

public class ResponseLoginUser {
    private int httpCode;
    private boolean status;
    private List<User> message;

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<User> getMessage() {
        return message;
    }

    public void setMessage(List<User> message) {
        this.message = message;
    }
}