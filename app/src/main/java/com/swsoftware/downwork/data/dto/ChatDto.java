package com.swsoftware.downwork.data.dto;

import com.google.gson.annotations.SerializedName;

public class ChatDto {
    int id;
    @SerializedName("last_message_id")
    int lastMessageId;
    @SerializedName("message_count")
    int messageCount;
    @SerializedName("user_info")
    UserDto userInfo;

    public int getId() {
        return id;
    }

    public int getLastMessageId() {
        return lastMessageId;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public UserDto getUserInfo() {
        return userInfo;
    }
}
