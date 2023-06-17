package com.swsoftware.downwork.domain.repository;

import androidx.lifecycle.MutableLiveData;

import com.swsoftware.downwork.data.dto.UserDto;

public interface UserInfoRepository {
    MutableLiveData<UserDto> getUserInfo(int userId);
}
