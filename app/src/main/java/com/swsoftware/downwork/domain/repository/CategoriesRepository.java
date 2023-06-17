package com.swsoftware.downwork.domain.repository;

import android.database.Observable;

import androidx.lifecycle.MutableLiveData;

import com.swsoftware.downwork.data.dto.CategoryDto;
import java.util.List;

public interface CategoriesRepository {
    MutableLiveData<List<CategoryDto>> getCategories();
}
