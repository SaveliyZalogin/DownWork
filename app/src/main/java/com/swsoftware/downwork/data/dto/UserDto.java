package com.swsoftware.downwork.data.dto;

import com.google.gson.annotations.SerializedName;

public class UserDto {
    int id;
    String name;
    @SerializedName("username")
    String userName;
    String about;
    String profession;
    @SerializedName("registration_date")
    String registrationDate;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getAbout() {
        return about;
    }

    public String getProfession() {
        return profession;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }
}
