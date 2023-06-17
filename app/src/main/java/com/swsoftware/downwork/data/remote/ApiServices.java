package com.swsoftware.downwork.data.remote;

import com.swsoftware.downwork.data.dto.CategoryDto;
import com.swsoftware.downwork.data.dto.ChatDto;
import com.swsoftware.downwork.data.dto.LoginDto;
import com.swsoftware.downwork.data.dto.MessageDto;
import com.swsoftware.downwork.data.dto.OrderDto;
import com.swsoftware.downwork.data.dto.ProjectDto;
import com.swsoftware.downwork.data.dto.UserDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("login")
    Call<LoginDto> login(
            @Query("email") String email,
            @Query("password") String password
    );

    @GET("registration")
    Call<LoginDto> registration(
            @Query("email") String email,
            @Query("username") String userName,
            @Query("name") String name,
            @Query("password") String password
    );

    @GET("getUserInfo")
    Call<UserDto> getUserInfo(@Query("user_id") int userId);
    @GET("getCategories")
    Call<List<CategoryDto>> getCategories();

    @GET("getProjects")
    Call<List<ProjectDto>> getProjects(@Query("category") int categoryId);

    @GET("getMessages")
    Call<List<MessageDto>> getMessages(@Query("chat_id") int chatId);

    @GET("searchProjects")
    Call<List<ProjectDto>> searchProjects(@Query("q") String query);

    @GET("getProjects")
    Call<List<ProjectDto>> getProjects();

    @GET("getChats")
    Call<List<ChatDto>> getChats(@Query("user_id") int userId);

    @GET("getOrders")
    Call<List<OrderDto>> getOrders(@Query("user_id") int userId);

    class Factory {
        private final String BASE_URL = "http://192.168.0.104:5000/";
        private final ApiServices apiServices;
        public Factory() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiServices = retrofit.create(ApiServices.class);
        }

        public ApiServices getApiServices() {
            return apiServices;
        }
    }
}