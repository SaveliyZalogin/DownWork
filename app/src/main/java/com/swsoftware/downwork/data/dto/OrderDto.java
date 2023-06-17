package com.swsoftware.downwork.data.dto;

import com.google.gson.annotations.SerializedName;

public class OrderDto {
    int state;
    @SerializedName("to_user")
    UserDto toUser;
    @SerializedName("from_user")
    UserDto fromUser;
    ProjectDto project;

    public int getState() {
        return state;
    }

    public UserDto getToUser() {
        return toUser;
    }

    public UserDto getFromUser() {
        return fromUser;
    }

    public ProjectDto getProject() {
        return project;
    }
}
