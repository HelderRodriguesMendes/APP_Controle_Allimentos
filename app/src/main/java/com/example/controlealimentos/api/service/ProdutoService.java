package com.example.controlealimentos.api.service;

import com.example.controlealimentos.app.model.Produto;
import com.example.controlealimentos.app.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProdutoService {
    @GET("produto/ProdudosDisponiveis")
    Call<List<Produto>> buscarProdutos();
}
