package com.swsoftware.downwork.presentation.projects;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;


import com.swsoftware.downwork.data.dto.ProjectDto;
import com.swsoftware.downwork.data.repository.ProjectsRepositoryImpl;
import com.swsoftware.downwork.domain.repository.ProjectsRepository;

import java.util.List;

public class ProjectsListViewModel {
    private final ProjectsRepository categoriesRepository = new ProjectsRepositoryImpl();

    public MutableLiveData<List<ProjectDto>> getProjects(int categoryId) {
        return categoriesRepository.getProjects(categoryId);
    }

    public MutableLiveData<List<ProjectDto>> searchProjects(String query) {
        return categoriesRepository.searchProjects(query);
    }

    public MutableLiveData<List<ProjectDto>> getProjects() {
        return categoriesRepository.getProjects();
    }
}
