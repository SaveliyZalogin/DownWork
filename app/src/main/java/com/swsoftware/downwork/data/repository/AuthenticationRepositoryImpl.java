package com.swsoftware.downwork.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.swsoftware.downwork.data.dto.LoginDto;
import com.swsoftware.downwork.data.remote.ApiServices;
import com.swsoftware.downwork.domain.repository.AuthenticationRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationRepositoryImpl implements AuthenticationRepository {
    private ApiServices apiServices;
    private final MutableLiveData<LoginDto> data;

    public AuthenticationRepositoryImpl() {
        apiServices = new ApiServices.Factory().getApiServices();
        data = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<LoginDto> login(String email, String password) {
        apiServices.login(email, password).enqueue(new Callback<LoginDto>() {
            @Override
            public void onResponse(Call<LoginDto> call, Response<LoginDto> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginDto> call, Throwable t) { t.printStackTrace(); }
        });
        return data;
    }

    @Override
    public MutableLiveData<LoginDto> registration(String username, String name, String email, String password) {
        apiServices.registration(username, name, email, password).enqueue(new Callback<LoginDto>() {
            @Override
            public void onResponse(Call<LoginDto> call, Response<LoginDto> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginDto> call, Throwable t) { t.printStackTrace(); }
        });
        return data;
    }
}
