package com.example.myapplication.api;

import com.example.myapplication.model.New;
import com.example.myapplication.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface MApi {
    @GET("posts")
    Call<List<New>> getData();

    @POST("posts")
    Call<New> createNew(@Body New aNew);

    @POST("posts")
    Observable<List<New>> getAllNew();

    @POST("users")
    Call<User> createUser(@Body User user);

    @FormUrlEncoded
    @POST("user")
    Call<User> updateUser(@Field("name") String name);

    // PUT method is used to update an existing resource if not found create new one
    // while POST method is used to create a new resource
    // while PATCH method is used just to update
    @PUT("posts/{id}")
    Call<New> putNew(@Path("id") int id , @Body New aNew);

    @PATCH("posts/{id}")
    Call<New> patchNew(@Path("id") int id , @Body New aNew);

    @DELETE("posts/{id}")
    Call<Void> deleteNew(@Path("id") int id);
}
