package com.swsoftware.downwork.domain.repository;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.swsoftware.downwork.data.dto.ProjectDto;

import java.util.List;

public interface ProjectsRepository {
    MutableLiveData<List<ProjectDto>> getProjects(int categoryId);
    MutableLiveData<List<ProjectDto>> getProjects();

     MutableLiveData<List<ProjectDto>> searchProjects(String query);
}
