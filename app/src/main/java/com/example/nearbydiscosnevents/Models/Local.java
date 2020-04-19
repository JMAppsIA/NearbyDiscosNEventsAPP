package com.example.nearbydiscosnevents.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Local {

    @SerializedName("idLocal")
    @Expose
    private Integer idLocal;

    @SerializedName("nombreLocal")
    @Expose
    private String nombreLocal;

    @SerializedName("imagenLocal")
    @Expose
    private String imagenLocal;

    @SerializedName("precioLocal")
    @Expose
    private Float precioLocal;

    @SerializedName("latitudLocal")
    @Expose
    private Float latitudLocal;

    @SerializedName("longitudLocal")
    @Expose
    private Float longitudLocal;

    @SerializedName("descripcionLocal")
    @Expose
    private String descripcionLocal;

    @SerializedName("telefonoLocal")
    @Expose
    private Integer telefonoLocal;

    @SerializedName("direccionLocal")
    @Expose
    private String direccionLocal;

    @SerializedName("idEstado")
    @Expose
    private Integer idEstado;

    @SerializedName("estadoLocal")
    @Expose
    private String estadoLocal;

    @SerializedName("rankingLocal")
    @Expose
    private Float rankingLocal;

    public Local() {
    }

    public Local(Integer idLocal, String nombreLocal, String imagenLocal, Float precioLocal, Float latitudLocal, Float longitudLocal, String descripcionLocal, Integer telefonoLocal, String direccionLocal, Integer idEstado, String estadoLocal, Float rankingLocal) {
        this.idLocal = idLocal;
        this.nombreLocal = nombreLocal;
        this.imagenLocal = imagenLocal;
        this.precioLocal = precioLocal;
        this.latitudLocal = latitudLocal;
        this.longitudLocal = longitudLocal;
        this.descripcionLocal = descripcionLocal;
        this.telefonoLocal = telefonoLocal;
        this.direccionLocal = direccionLocal;
        this.idEstado = idEstado;
        this.estadoLocal = estadoLocal;
        this.rankingLocal = rankingLocal;
    }

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getImagenLocal() {
        return imagenLocal;
    }

    public void setImagenLocal(String imagenLocal) {
        this.imagenLocal = imagenLocal;
    }

    public Float getPrecioLocal() {
        return precioLocal;
    }

    public void setPrecioLocal(Float precioLocal) {
        this.precioLocal = precioLocal;
    }

    public Float getLatitudLocal() {
        return latitudLocal;
    }

    public void setLatitudLocal(Float latitudLocal) {
        this.latitudLocal = latitudLocal;
    }

    public Float getLongitudLocal() {
        return longitudLocal;
    }

    public void setLongitudLocal(Float longitudLocal) {
        this.longitudLocal = longitudLocal;
    }

    public String getDescripcionLocal() {
        return descripcionLocal;
    }

    public void setDescripcionLocal(String descripcionLocal) {
        this.descripcionLocal = descripcionLocal;
    }

    public Integer getTelefonoLocal() {
        return telefonoLocal;
    }

    public void setTelefonoLocal(Integer telefonoLocal) {
        this.telefonoLocal = telefonoLocal;
    }

    public String getDireccionLocal() {
        return direccionLocal;
    }

    public void setDireccionLocal(String direccionLocal) {
        this.direccionLocal = direccionLocal;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstadoLocal() {
        return estadoLocal;
    }

    public void setEstadoLocal(String estadoLocal) {
        this.estadoLocal = estadoLocal;
    }

    public Float getRankingLocal() {
        return rankingLocal;
    }

    public void setRankingLocal(Float rankingLocal) {
        this.rankingLocal = rankingLocal;
    }
}
