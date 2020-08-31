package com.example.controlealimentos.app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.controlealimentos.R;
import com.example.controlealimentos.api.controller.Retrofit_URL;
import com.example.controlealimentos.api.service.ProdutoService;
import com.example.controlealimentos.api.service.UsuarioService;
import com.example.controlealimentos.app.AdapterProduto;
import com.example.controlealimentos.app.model.Produto;
import com.example.controlealimentos.app.model.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List_ProdutosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Produto> produtos = new ArrayList<>();

    Retrofit_URL retrofit = new Retrofit_URL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__produtos);

        recyclerView = findViewById(R.id.recyclerProdutos);

        buscarProdutos();
        //CONFIGURAR ADAPTER - PEGA OS DADOS E MONTA UMA VISUALIZAÇÃO
        AdapterProduto adapterProduto = new AdapterProduto();

        //CONFIGURAR RECYCLERVIEW
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()); //GERENCIADOR DE LAYOUT
        recyclerView.setLayoutManager(layoutManager); //CONFIGURANDO O RECYCLERVIEW UTILIZADO NA TELA
        recyclerView.setHasFixedSize(true); //OTIMIZANDO PARA UM TAMANHO FIXO
        recyclerView.setAdapter(adapterProduto);

    }

    private void buscarProdutos(){
        System.out.println("TESTE 1");
        ProdutoService produtoService = retrofit.URLBase().create(ProdutoService.class);
        System.out.println("TESTE 2");
        Call<List<Produto>> call = produtoService.buscarProdutos();
        System.out.println("TESTE 3");
        call.enqueue(new Callback<List<Produto>>() {

            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                System.out.println("TESTE 4");
                if(response.isSuccessful()){
                    produtos = response.body();
                    for(int i = 0; i < produtos.size(); i++){
                        Produto p = produtos.get(i);
                        Log.d("resultado", "resultado: " + p.getNome());
                        System.out.println("resultado: " + p.getNome());
                    }
                }else{
                    System.out.println("TESTE 5");
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                System.out.println("TESTE 6");
                if(produtos.isEmpty()){
                    System.out.println("vazia");
                }
            }
        });
    }

}