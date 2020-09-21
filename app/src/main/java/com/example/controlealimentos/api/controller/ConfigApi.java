package com.example.controlealimentos.api.controller;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConfigApi {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String configDataApi(String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate localDate = LocalDate.parse(data, formatter);

        String pronta = localDate.format(fmt);

        return pronta;
    }
}
