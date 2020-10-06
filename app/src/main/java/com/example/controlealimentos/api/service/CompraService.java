package com.example.controlealimentos.api.service;

import com.example.controlealimentos.app.model.CompraDTO;
import com.example.controlealimentos.app.model.ProdutoDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CompraService {
    @POST("alimentos/compra")
    Call<CompraDTO> salvarCompra(@Body CompraDTO compraDTO);

    @GET("alimentos/compra/pesquisarSupermercado?")
    Call<List<CompraDTO>> buscarCompraSupermercado(@Query("nome") String nome);

    @GET("alimentos/compra/listaCompras")
    Call<List<CompraDTO>> buscarComprasDisponiveis();

    @PUT("alimentos/compra/alterar/{id}")
    Call<CompraDTO> atualizar(@Path("id") Long id, @Body CompraDTO compraDTO);
}
