package com.example.myapplication.controller;

import com.example.myapplication.api.MApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientController {
    private static final String url = "https://jsonplaceholder.typicode.com/";
    private static ClientController clientController; //singleton
    private static Retrofit retrofit;

    ClientController(){
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ClientController getInstance(){
        if(clientController != null){
            clientController = new ClientController();
        }
        return clientController;
    }

    MApi getApi(){
        return retrofit.create(MApi.class);
    }
}
