package com.swsoftware.downwork.presentation.main;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swsoftware.downwork.R;
import com.swsoftware.downwork.data.dto.CategoryDto;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private final List<CategoryDto> categories;
    private final OnItemClickListener onItemClickListener;

    public CategoriesAdapter(List<CategoryDto> categories, OnItemClickListener onItemClick) {
        this.categories = categories;
        onItemClickListener = onItemClick;
    }

    public interface OnItemClickListener {
        void onClick(int itemId, String itemName);
    }
    static public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;
        TextView projectsCount;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            projectsCount = itemView.findViewById(R.id.projectsCount);
        }
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_layout, parent, false);
        return new CategoriesViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        CategoryDto category = categories.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(holder.getAdapterPosition() + 1, category.getName());
            }
        });
        holder.icon.getBackground().setAlpha(25);
        holder.title.setText(category.getName());
        holder.projectsCount.setText(category.getProjectsCount() + " проектов");
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
