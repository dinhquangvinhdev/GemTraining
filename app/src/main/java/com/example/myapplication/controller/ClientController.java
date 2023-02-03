package com.example.myapplication.controller;

import com.example.myapplication.api.MApi;
import com.example.myapplication.model.New;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientController {
    private static final String url = "https://jsonplaceholder.typicode.com/";
    private static ClientController clientController; //singleton
    private static Retrofit retrofit;

    ClientController(){
        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY); // display all

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized ClientController getInstance(){
        if(clientController == null){
            clientController = new ClientController();
        }
        return clientController;
    }

    public MApi getApi(){
        return retrofit.create(MApi.class);
    }
}
