package com.example.controlealimentos.api.service;

import com.example.controlealimentos.app.model.CompraDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CompraService {
    @POST("alimentos/compra")
    Call<CompraDTO> salvarCompra(@Body CompraDTO compraDTO);

    @GET("alimentos/compra/pesquisarSupermercado?")
    Call<List<CompraDTO>> buscarCompraSupermercado(@Query("nome") String nome);

    @GET("alimentos/compra/listaCompras")
    Call<List<CompraDTO>> buscarComprasDisponiveis();
}
