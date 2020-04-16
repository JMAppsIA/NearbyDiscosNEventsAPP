package com.example.nearbydiscosnevents.Models.Request.LoginUser;

import java.util.List;

public class Request {
    List<String> request;

    List<Payload> payload;


    public Request(List<String> request) {
        this.request = request;
    }


}
