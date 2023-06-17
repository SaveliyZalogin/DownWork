package com.swsoftware.downwork.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.swsoftware.downwork.data.dto.CategoryDto;
import com.swsoftware.downwork.data.dto.OrderDto;
import com.swsoftware.downwork.data.remote.ApiServices;
import com.swsoftware.downwork.domain.repository.OrdersRepository;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersRepositoryImpl implements OrdersRepository {
    ApiServices apiServices;
    private final MutableLiveData<List<OrderDto>> data;

    public OrdersRepositoryImpl() {
        apiServices = new ApiServices.Factory().getApiServices();
        data = new MutableLiveData<List<OrderDto>>();
    }

    @Override
    public MutableLiveData<List<OrderDto>> getOrders(int userId) {
        apiServices.getOrders(userId).enqueue(new Callback<List<OrderDto>>() {
            @Override
            public void onResponse(Call<List<OrderDto>> call, Response<List<OrderDto>> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<OrderDto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return data;
    }
}
