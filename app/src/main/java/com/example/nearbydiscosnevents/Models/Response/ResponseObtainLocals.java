package com.example.nearbydiscosnevents.Models.Response;

import com.example.nearbydiscosnevents.Models.Local;

import java.util.List;

public class ResponseObtainLocals {
    private int httpCode;
    private boolean status;
    private List<Local> message;

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

    public List<Local> getMessage() {
        return message;
    }

    public void setMessage(List<Local> message) {
        this.message = message;
    }
}
