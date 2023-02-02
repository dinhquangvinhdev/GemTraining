package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class New {
    @SerializedName("userId")
    int idUser;
    int id;
    String title;
    String body;

    public New(int idUser, int id, String title, String body) {
        this.idUser = idUser;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public New(int id , String title , String body){
        this.idUser = 1;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
