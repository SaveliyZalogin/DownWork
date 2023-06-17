package com.swsoftware.downwork.presentation.auth;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swsoftware.downwork.data.dto.LoginDto;
import com.swsoftware.downwork.data.repository.AuthenticationRepositoryImpl;
import com.swsoftware.downwork.domain.repository.AuthenticationRepository;

import java.util.List;

public class AuthenticationViewModel extends ViewModel {
    private final AuthenticationRepository authenticationRepository = new AuthenticationRepositoryImpl();

    public MutableLiveData<LoginDto> login(String email, String password) {
        return authenticationRepository.login(email, password);
    }

    public MutableLiveData<LoginDto> registration(String username, String name, String email, String password) {
        return authenticationRepository.registration(username, name, email, password);
    }
}
