package com.example.nearbydiscosnevents.Models.Request.LoginUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class LoginUserRequest {
    int ApiKey;
    String nomUsuario;
    String passUsuario;

    public LoginUserRequest(int ApiKey, String nomUsuario, String passUsuario) {
        this.ApiKey = ApiKey;
        this.nomUsuario = nomUsuario;
        this.passUsuario = passUsuario;
    }
}








