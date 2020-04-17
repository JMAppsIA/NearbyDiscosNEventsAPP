package com.example.nearbydiscosnevents.Models.Response;

public class ResponseCreateUser {

    private int httpCode;
    private boolean status;
    private String message;

    public ResponseCreateUser() {
    }

    public ResponseCreateUser(int httpCode, boolean status, String message) {
        this.httpCode = httpCode;
        this.status = status;
        this.message = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode){
        this.httpCode = httpCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
