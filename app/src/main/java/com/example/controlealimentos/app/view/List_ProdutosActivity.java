package com.example.controlealimentos.app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.controlealimentos.R;
import com.example.controlealimentos.app.AdapterProduto;

public class List_ProdutosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

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


}