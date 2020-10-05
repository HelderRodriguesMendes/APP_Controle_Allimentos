package com.example.controlealimentos.api.service;

import com.example.controlealimentos.app.model.CompraDTO;
import com.example.controlealimentos.app.model.ProdutoDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProdutoService {
    @GET("produto/ProdudosDisponiveis")
    Call<List<ProdutoDTO>> buscarProdutosDisponiveis();

    @GET("produto/buscarProduto?")
    Call<List<ProdutoDTO>> buscarProdutosNome(@Query("nome") String nome);

    @POST("produto")
    Call<ProdutoDTO> salvarProduto(@Body ProdutoDTO produtoDTO);
}
