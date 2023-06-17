package com.swsoftware.downwork.data.repository;

import android.media.MicrophoneDirection;

import androidx.lifecycle.MutableLiveData;

import com.swsoftware.downwork.data.dto.UserDto;
import com.swsoftware.downwork.data.remote.ApiServices;
import com.swsoftware.downwork.domain.repository.UserInfoRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoRepositoryImpl implements UserInfoRepository {
    private final ApiServices apiServices;
    private final MutableLiveData<UserDto> data;

    public UserInfoRepositoryImpl() {
        apiServices = new ApiServices.Factory().getApiServices();
        data = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<UserDto> getUserInfo(int userId) {
        apiServices.getUserInfo(userId).enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) { t.printStackTrace(); }
        });
        return data;
    }
}
