package com.swsoftware.downwork.data.dto;

import com.google.gson.annotations.SerializedName;

public class ProjectDto {
    int id;
    @SerializedName("author_info")
    UserDto authorInfo;
    String title;
    String description;
    int price;
    float rating;
    @SerializedName("reviews_count")
    int reviewsCount;

    public int getId() {
        return id;
    }

    public UserDto getAuthorInfo() {
        return authorInfo;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }
}
