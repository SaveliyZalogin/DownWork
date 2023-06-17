package com.swsoftware.downwork.presentation.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.swsoftware.downwork.R;
import com.swsoftware.downwork.data.dto.CategoryDto;
import com.swsoftware.downwork.presentation.projects.ProjectListFragment;

import java.util.List;

public class MainFragment extends Fragment {
    private final MainViewModel mainViewModel = new MainViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        ProgressBar loading = view.findViewById(R.id.progress);

        mainViewModel.getCategories().observe(getViewLifecycleOwner(), new Observer<List<CategoryDto>>() {
            @Override
            public void onChanged(List<CategoryDto> categoryDtoList) {
                if (categoryDtoList.size() > 0) {
                    CategoriesAdapter adapter = new CategoriesAdapter(categoryDtoList, new CategoriesAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int itemId, String itemName) {
                            ProjectListFragment fragment = new ProjectListFragment();
                            Bundle args = new Bundle();
                            args.putInt("categoryId", itemId);
                            args.putString("categoryName", itemName);
                            fragment.setArguments(args);
                            getParentFragmentManager().beginTransaction()
                                    .replace(R.id.frameLayout, fragment)
                                    .addToBackStack("projectsListCategory")
                                    .setReorderingAllowed(false)
                                    .commit();
                        }
                    });
                    recyclerView.setAdapter(adapter);
                    loading.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}