package com.swsoftware.downwork.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.swsoftware.downwork.data.dto.MessageDto;
import com.swsoftware.downwork.data.remote.ApiServices;
import com.swsoftware.downwork.domain.repository.MessagesRepository;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesRepositoryImpl implements MessagesRepository {
    private final ApiServices apiServices;
    private final MutableLiveData<List<MessageDto>> data;

    public MessagesRepositoryImpl() {
        apiServices = new ApiServices.Factory().getApiServices();
        data = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<List<MessageDto>> getMessages(int chatId) {
        apiServices.getMessages(chatId).enqueue(new Callback<List<MessageDto>>() {
            @Override
            public void onResponse(Call<List<MessageDto>> call, Response<List<MessageDto>> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MessageDto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return data;
    }
}
