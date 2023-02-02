package com.example.myapplication.api;

import com.example.myapplication.model.New;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MApi {
    @GET("posts")
    Call<List<New>> getData();
}
