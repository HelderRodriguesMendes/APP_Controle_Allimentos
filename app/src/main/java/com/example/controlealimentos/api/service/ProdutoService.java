package com.example.controlealimentos.api.service;

import com.example.controlealimentos.app.model.Produto;
import com.example.controlealimentos.app.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProdutoService {
    @GET("produto/ProdudosDisponiveis")
    Call<List<Produto>> buscarProdutosDisponiveis();


    @GET("produto/buscarProduto?")
    Call<List<Produto>> buscarProdutosNome(@Query("nome") String nome);
}
