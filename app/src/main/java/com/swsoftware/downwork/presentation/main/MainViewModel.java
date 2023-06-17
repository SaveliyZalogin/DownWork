package com.swsoftware.downwork.presentation.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swsoftware.downwork.data.dto.CategoryDto;
import com.swsoftware.downwork.data.repository.CategoriesRepositoryImpl;
import com.swsoftware.downwork.domain.repository.CategoriesRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final CategoriesRepository categoriesRepository = new CategoriesRepositoryImpl();

    public MutableLiveData<List<CategoryDto>> getCategories() {
        return categoriesRepository.getCategories();
    }

}
