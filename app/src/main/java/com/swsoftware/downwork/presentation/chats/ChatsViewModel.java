package com.swsoftware.downwork.presentation.chats;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swsoftware.downwork.data.dto.ChatDto;
import com.swsoftware.downwork.data.repository.ChatsRepositoryImpl;
import com.swsoftware.downwork.domain.repository.ChatsRepository;

import java.util.List;

public class ChatsViewModel extends ViewModel {
    private final ChatsRepository chatsRepository = new ChatsRepositoryImpl();

    public MutableLiveData<List<ChatDto>> getChats(int userId) {
        return chatsRepository.getChats(userId);
    }
}