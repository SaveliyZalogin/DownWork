package com.swsoftware.downwork.presentation.profile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swsoftware.downwork.data.dto.UserDto;
import com.swsoftware.downwork.data.repository.UserInfoRepositoryImpl;
import com.swsoftware.downwork.domain.repository.UserInfoRepository;

public class ProfileViewModel extends ViewModel {
    private final UserInfoRepository userInfoRepository = new UserInfoRepositoryImpl();

    MutableLiveData<UserDto> getUserInfo(int userId) {
        return userInfoRepository.getUserInfo(userId);
    }
}
