package com.swsoftware.downwork.domain.repository;

import androidx.lifecycle.MutableLiveData;

import com.swsoftware.downwork.data.dto.OrderDto;

import java.util.List;

public interface OrdersRepository {
    MutableLiveData<List<OrderDto>> getOrders(int userId);
}
