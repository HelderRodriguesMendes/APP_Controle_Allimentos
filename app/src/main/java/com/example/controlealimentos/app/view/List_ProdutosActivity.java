package com.example.controlealimentos.app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.controlealimentos.R;
import com.example.controlealimentos.api.controller.Retrofit_URL;
import com.example.controlealimentos.api.service.ProdutoService;
import com.example.controlealimentos.api.service.UsuarioService;
import com.example.controlealimentos.app.AdapterProduto;
import com.example.controlealimentos.app.model.Produto;
import com.example.controlealimentos.app.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List_ProdutosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    Retrofit_URL retrofit = new Retrofit_URL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__produtos);

        recyclerView = findViewById(R.id.recyclerProdutos);


        //CONFIGURAR ADAPTER - PEGA OS DADOS E MONTA UMA VISUALIZAÇÃO
        AdapterProduto adapterProduto = new AdapterProduto();

        //CONFIGURAR RECYCLERVIEW
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()); //GERENCIADOR DE LAYOUT
        recyclerView.setLayoutManager(layoutManager); //CONFIGURANDO O RECYCLERVIEW UTILIZADO NA TELA
        recyclerView.setHasFixedSize(true); //OTIMIZANDO PARA UM TAMANHO FIXO
        recyclerView.setAdapter(adapterProduto);

    }

    private void buscarProdutos(){
        ProdutoService produtoService = retrofit.URLBase().create(ProdutoService.class);
        Call<List<Produto>> call = produtoService.buscarProdutos();
        call.enqueue(new Callback<List<Produto>>() {

            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                if(response.isSuccessful()){
                    List<Produto> produtos = response.body();
                    for(Produto p : produtos){
                        Toast.makeText(List_ProdutosActivity.this, "NOME DO PRODUTO: " + p.getNome(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {

            }
        });
    }

}