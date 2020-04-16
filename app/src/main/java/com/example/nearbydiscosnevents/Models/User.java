package com.example.nearbydiscosnevents.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("personId")
    @Expose
    private Integer personId;

    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("secondName")
    @Expose
    private String secondName;

    @SerializedName("firstLastName")
    @Expose
    private String firstLastName;

    @SerializedName("secondLastName")
    @Expose
    private String secondLastName;

    @SerializedName("fullName")
    @Expose
    private String fullName;

    @SerializedName("documentType")
    @Expose
    private int documentType;

    @SerializedName("documentNumber")
    @Expose
    private String documentNumber;

    @SerializedName("bornDate")
    @Expose
    private String bornDate;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("genre")
    @Expose
    private String genre;

    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("mobileNumber")
    @Expose
    private int mobileNumber;

    @SerializedName("userStatus")
    @Expose
    private String userStatus;

    @SerializedName("statudID")
    @Expose
    private int statudID;

    public User() {
    }

    public User(Integer personId, String firstName, String secondName, String firstLastName, String secondLastName, String fullName, int documentType, String documentNumber, String bornDate, String address, String genre, String userName, String email, int mobileNumber, String userStatus, int statudID) {
        this.personId = personId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.fullName = fullName;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.bornDate = bornDate;
        this.address = address;
        this.genre = genre;
        this.userName = userName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.userStatus = userStatus;
        this.statudID = statudID;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDocumentType() {
        return documentType;
    }

    public void setDocumentType(int documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public int getStatudID() {
        return statudID;
    }

    public void setStatudID(int statudID) {
        this.statudID = statudID;
    }
}


