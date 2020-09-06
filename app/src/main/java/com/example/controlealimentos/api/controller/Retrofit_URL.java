package com.example.controlealimentos.api.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_URL {

    public Retrofit URLBase(){
        Retrofit retrofit;

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        retrofit = new Retrofit.Builder().baseUrl("http:192.168.1.5:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        return retrofit;
    }
}
