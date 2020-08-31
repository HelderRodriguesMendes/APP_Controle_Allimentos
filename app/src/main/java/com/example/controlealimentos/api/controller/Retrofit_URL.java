package com.example.controlealimentos.api.controller;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_URL {

    public Retrofit URLBase(){
        Retrofit retrofit;

        retrofit = new Retrofit.Builder().baseUrl("http:192.168.1.3:8080")
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
