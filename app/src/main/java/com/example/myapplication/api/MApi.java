package com.example.myapplication.api;

import com.example.myapplication.model.New;
import com.example.myapplication.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MApi {
    @GET("posts")
    Call<List<New>> getData();

    @POST("posts")
    Call<New> createNew(@Body New aNew);

    @POST("users")
    Call<User> createUser(@Body User user);
}
