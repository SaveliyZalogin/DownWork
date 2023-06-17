package com.swsoftware.downwork.domain.repository;

import androidx.lifecycle.MutableLiveData;

import com.swsoftware.downwork.data.dto.LoginDto;

import java.util.List;

public interface AuthenticationRepository {
    MutableLiveData<LoginDto> login(String email, String password);
    MutableLiveData<LoginDto> registration(String username, String name, String email, String password);
}
