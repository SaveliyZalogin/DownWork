package com.swsoftware.downwork.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.swsoftware.downwork.data.dto.ChatDto;
import com.swsoftware.downwork.data.dto.OrderDto;
import com.swsoftware.downwork.data.remote.ApiServices;
import com.swsoftware.downwork.domain.repository.ChatsRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatsRepositoryImpl implements ChatsRepository {
    ApiServices apiServices;
    private final MutableLiveData<List<ChatDto>> data;

    public ChatsRepositoryImpl() {
        apiServices = new ApiServices.Factory().getApiServices();
        data = new MutableLiveData<List<ChatDto>>();
    }

    @Override
    public MutableLiveData<List<ChatDto>> getChats(int userId) {
        apiServices.getChats(userId).enqueue(new Callback<List<ChatDto>>() {
            @Override
            public void onResponse(Call<List<ChatDto>> call, Response<List<ChatDto>> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ChatDto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return data;
    }
}
