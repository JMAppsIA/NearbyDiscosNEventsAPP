package com.example.nearbydiscosnevents.Models.Request.LoginUser;

public class Trace {
    String consumerId, channelCode;

    public Trace(String consumerId, String channelCode) {
        this.consumerId = consumerId;
        this.channelCode = channelCode;
    }
}