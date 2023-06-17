package com.swsoftware.downwork.presentation.messaging;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swsoftware.downwork.data.dto.MessageDto;
import com.swsoftware.downwork.data.repository.MessagesRepositoryImpl;
import com.swsoftware.downwork.domain.repository.MessagesRepository;

import java.util.List;

public class ChatViewModel extends ViewModel {
    private final MessagesRepository messagesRepository = new MessagesRepositoryImpl();

    public MutableLiveData<List<MessageDto>> getMessages(int chatId) {
        return messagesRepository.getMessages(chatId);
    }

}
