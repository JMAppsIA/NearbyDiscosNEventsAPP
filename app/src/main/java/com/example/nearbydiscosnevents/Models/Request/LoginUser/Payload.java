package com.example.nearbydiscosnevents.Models.Request.LoginUser;

public class Payload {
    String nomUsuario,passUsuario;

    public Payload(String nomUsuario, String passUsuario) {
        this.nomUsuario = nomUsuario;
        this.passUsuario = passUsuario;
    }
}