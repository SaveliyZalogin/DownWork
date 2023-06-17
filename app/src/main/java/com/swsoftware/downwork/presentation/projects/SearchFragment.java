package com.swsoftware.downwork.presentation.projects;

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
import com.swsoftware.downwork.data.dto.ProjectDto;
import com.swsoftware.downwork.presentation.MainActivity;

import java.util.List;

public class SearchFragment extends Fragment {
    private ProjectsListViewModel projectsListViewModel = new ProjectsListViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        ProgressBar loading = view.findViewById(R.id.progress);

        String query = ((MainActivity) requireActivity()).bottomBar.searchBar.getText().toString();

        ((MainActivity) requireActivity()).collapsingToolbarLayout.setTitle(query);

        projectsListViewModel.searchProjects(query)
                .observe(getViewLifecycleOwner(), new Observer<List<ProjectDto>>() {
                    @Override
                    public void onChanged(List<ProjectDto> projectDtoList) {
                        if (projectDtoList.size() > 0) {
                            ProjectsAdapter adapter = new ProjectsAdapter(projectDtoList);
                            recyclerView.setAdapter(adapter);
                            loading.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity activity = (MainActivity) requireActivity();
        activity.bottomBar.searchBar.clearFocus();
        activity.bottomBar.searchBar.setText("");
     }
}