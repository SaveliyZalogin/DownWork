package com.swsoftware.downwork.data.repository;

import androidx.lifecycle.MutableLiveData;
import com.swsoftware.downwork.data.dto.CategoryDto;
import com.swsoftware.downwork.data.remote.ApiServices;
import com.swsoftware.downwork.domain.repository.CategoriesRepository;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoriesRepositoryImpl implements CategoriesRepository {
    ApiServices apiServices;
    private final MutableLiveData<List<CategoryDto>> data;

    public CategoriesRepositoryImpl() {
        apiServices = new ApiServices.Factory().getApiServices();
        data = new MutableLiveData<List<CategoryDto>>();
    }

    @Override
    public MutableLiveData<List<CategoryDto>> getCategories() {
        apiServices.getCategories().enqueue(new Callback<List<CategoryDto>>() {
            @Override
            public void onResponse(Call<List<CategoryDto>> call, Response<List<CategoryDto>> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<CategoryDto>> call, Throwable t) { t.printStackTrace(); }
        });
        return data;
    }
}
