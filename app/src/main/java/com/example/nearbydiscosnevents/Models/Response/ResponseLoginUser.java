package com.example.nearbydiscosnevents.Models.Response;

import com.example.nearbydiscosnevents.Models.User;

import java.util.List;

public class ResponseLoginUser {
    private int status;
    private boolean success;
    private List<User> message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<User> getMessage() {
        return message;
    }

    public void setMessage(List<User> message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "status:" + status +
                ", success:" + success +
                ", message:" + message +
                '}';
    }
}