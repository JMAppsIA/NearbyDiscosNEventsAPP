package com.example.nearbydiscosnevents.Models.Response;

import com.example.nearbydiscosnevents.Models.GenreType;

import java.util.List;

public class ResponseGenreType {

    private int httpCode;
    private boolean status;
    private List<GenreType> message;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public List<GenreType> getMessage() {
        return message;
    }

    public void setMessage(List<GenreType> message) {
        this.message = message;
    }

}
