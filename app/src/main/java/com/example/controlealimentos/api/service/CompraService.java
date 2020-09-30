package com.example.controlealimentos.api.service;

import com.example.controlealimentos.app.model.CompraDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CompraService {
    @POST("alimentos/compra")
    Call<CompraDTO> salvarCompra(@Body CompraDTO compraDTO);
}
