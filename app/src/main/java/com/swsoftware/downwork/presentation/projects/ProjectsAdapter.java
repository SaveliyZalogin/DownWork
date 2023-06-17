package com.swsoftware.downwork.presentation.projects;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swsoftware.downwork.R;
import com.swsoftware.downwork.data.dto.ProjectDto;

import java.util.List;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsViewHolder> {

    private final List<ProjectDto> projects;

    public ProjectsAdapter(List<ProjectDto> projects) {
        this.projects = projects;
    }
    static public class ProjectsViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView author;
        TextView rating;
        TextView title;
        TextView price;
        public ProjectsViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            author = itemView.findViewById(R.id.author);
            rating = itemView.findViewById(R.id.rating);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
        }
    }

    @NonNull
    @Override
    public ProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_preview_layout, parent, false);
        return new ProjectsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProjectsViewHolder holder, int position) {
        ProjectDto project = projects.get(position);
        holder.author.setText(project.getAuthorInfo().getUserName());
        holder.rating.setText(project.getRating() + " (" + project.getReviewsCount() + ")");
        holder.title.setText(project.getTitle());
        holder.price.setText(project.getPrice() + "₽");
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }
}
