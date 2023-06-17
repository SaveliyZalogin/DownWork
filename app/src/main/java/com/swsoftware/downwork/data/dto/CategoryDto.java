package com.swsoftware.downwork.data.dto;

import com.google.gson.annotations.SerializedName;

public class CategoryDto {
    int id;
    String name;
    @SerializedName("projects_count")
    int projectsCount;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getProjectsCount() {
        return projectsCount;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + projectsCount;
    }
}
