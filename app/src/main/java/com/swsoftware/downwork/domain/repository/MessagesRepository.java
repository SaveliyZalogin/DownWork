package com.swsoftware.downwork.domain.repository;

import androidx.lifecycle.MutableLiveData;

import com.swsoftware.downwork.data.dto.MessageDto;

import java.util.List;

public interface MessagesRepository {
    MutableLiveData<List<MessageDto>> getMessages(int chatId);
}
