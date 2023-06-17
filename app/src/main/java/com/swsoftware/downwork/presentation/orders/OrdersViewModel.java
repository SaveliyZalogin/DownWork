package com.swsoftware.downwork.presentation.orders;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swsoftware.downwork.data.dto.OrderDto;
import com.swsoftware.downwork.data.repository.OrdersRepositoryImpl;
import com.swsoftware.downwork.domain.repository.OrdersRepository;

import java.util.List;

public class OrdersViewModel extends ViewModel {
    private final OrdersRepository chatsRepository = new OrdersRepositoryImpl();

    public MutableLiveData<List<OrderDto>> getOrders(int userId) {
        return chatsRepository.getOrders(userId);
    }
}
