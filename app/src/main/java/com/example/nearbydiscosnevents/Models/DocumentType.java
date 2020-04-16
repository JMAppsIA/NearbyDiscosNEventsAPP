package com.example.nearbydiscosnevents.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentType {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public DocumentType(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public DocumentType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

