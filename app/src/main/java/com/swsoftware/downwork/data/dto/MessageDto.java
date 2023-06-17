package com.swsoftware.downwork.data.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class MessageDto {
    int id;
    @SerializedName("from_user")
    int fromUser;
    String message;
    int type;

    public MessageDto(int fromUser, String message) {
        this.id = 1;
        this.fromUser = fromUser;
        this.message = message;
        this.type = 0;
    }

    public int getId() {
        return id;
    }

    public int getFromUser() {
        return fromUser;
    }

    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }

    @NonNull
    @Override
    public String toString() {
        return message + " " + fromUser;
    }
}
