package com.swsoftware.downwork.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.swsoftware.downwork.data.dto.CategoryDto;
import com.swsoftware.downwork.data.dto.ProjectDto;
import com.swsoftware.downwork.data.remote.ApiServices;
import com.swsoftware.downwork.domain.repository.ProjectsRepository;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectsRepositoryImpl implements ProjectsRepository {

    ApiServices apiServices;
    private final MutableLiveData<List<ProjectDto>> data;

    public ProjectsRepositoryImpl() {
        apiServices = new ApiServices.Factory().getApiServices();
        data = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<List<ProjectDto>> getProjects(int categoryId) {
        apiServices.getProjects(categoryId).enqueue(new Callback<List<ProjectDto>>() {
            @Override
            public void onResponse(Call<List<ProjectDto>> call, Response<List<ProjectDto>> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProjectDto>> call, Throwable t) { t.printStackTrace(); }
        });
        return data;
    }

    @Override
    public MutableLiveData<List<ProjectDto>> getProjects() {
        return data;
    }

    @Override
    public MutableLiveData<List<ProjectDto>> searchProjects(String query) {
        apiServices.searchProjects(query).enqueue(new Callback<List<ProjectDto>>() {
            @Override
            public void onResponse(Call<List<ProjectDto>> call, Response<List<ProjectDto>> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProjectDto>> call, Throwable t) { t.printStackTrace(); }
        });
        return data;
    }
}
