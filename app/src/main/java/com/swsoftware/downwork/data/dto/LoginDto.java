package com.swsoftware.downwork.data.dto;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class LoginDto {
    int status;
    String message;
    @SerializedName("mobile_token")
    @Nullable String mobileToken = null;
    @SerializedName("user_id")
    @Nullable Integer userId = null;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Nullable
    public String getMobileToken() {
        return mobileToken;
    }

    @Nullable
    public Integer getUserId() {
        return userId;
    }
}
