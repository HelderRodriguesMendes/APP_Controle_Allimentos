package com.example.controlealimentos.app.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.controlealimentos.R;
import com.example.controlealimentos.app.controller.RecyclerItemClickListener;
import com.example.controlealimentos.api.controller.Retrofit_URL;
import com.example.controlealimentos.api.service.ProdutoService;

import com.example.controlealimentos.app.controller.AdapterProduto;
import com.example.controlealimentos.app.model.Produto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List_ProdutosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageButton btnPesquisar;

    Retrofit_URL retrofit = new Retrofit_URL();

    List<Produto> listaProdutos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__produtos);
        recyclerView = findViewById(R.id.recyclerProdutos);
        btnPesquisar = findViewById(R.id.btnPesquisar);

        buscarProdutos();

        btnPesquisar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(List_ProdutosActivity.this, "Clico no botao", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buscarProdutos(){
        ProdutoService produtoService = retrofit.URLBase().create(ProdutoService.class);
        Call<List<Produto>> call = produtoService.buscarProdutos();
        call.enqueue(new Callback<List<Produto>>() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                if(response.isSuccessful()){
                    listaProdutos = response.body();
                    listaProdutos(listaProdutos);
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void listaProdutos(final List<Produto> listaProdutos){

        for(Produto p : listaProdutos){
            LocalDate dt = LocalDate.parse(p.getDataValidade());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = dt.format(formatter);

            p.setDataValidade(dataFormatada);
        }

        //CONFIGURAR ADAPTER - PEGA OS DADOS E MONTA UMA VISUALIZAÇÃO
        AdapterProduto adapterProduto = new AdapterProduto(listaProdutos);

        //CONFIGURAR RECYCLERVIEW
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()); //GERENCIADOR DE LAYOUT
        recyclerView.setLayoutManager(layoutManager); //CONFIGURANDO O RECYCLERVIEW UTILIZADO NA TELA
        recyclerView.setHasFixedSize(true); //OTIMIZANDO PARA UM TAMANHO FIXO
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL)); //ADICIONA UMA LINHA ENTRE CADA ITEM DA LISTA
        recyclerView.setAdapter(adapterProduto);

        //EVENTO DE CLICK
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Produto produto = listaProdutos.get(position);
                                Toast.makeText(List_ProdutosActivity.this, "CLICK CURTO NOME: " + produto.getNome(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(List_ProdutosActivity.this, "CLICK CURTO ID: " + produto.getId(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(List_ProdutosActivity.this, "CLICK CURTO TIPO: " + produto.getTipo(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Produto produto = listaProdutos.get(position);
                                Toast.makeText(List_ProdutosActivity.this, "CLICK LONGO NOME: " + produto.getNome(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(List_ProdutosActivity.this, "CLICK LONGO TIPO: " + produto.getTipo(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(List_ProdutosActivity.this, "CLICK LONGO ID: " + produto.getId(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );
    }

}