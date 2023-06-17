package com.swsoftware.downwork.domain.repository;

import androidx.lifecycle.MutableLiveData;

import com.swsoftware.downwork.data.dto.ChatDto;

import java.util.List;

public interface ChatsRepository {
    MutableLiveData<List<ChatDto>> getChats(int userId);
}
