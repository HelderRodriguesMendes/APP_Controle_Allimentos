package com.example.controlealimentos.app.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.controlealimentos.R;
import com.example.controlealimentos.app.controller.RecyclerItemClickListener;
import com.example.controlealimentos.api.controller.Retrofit_URL;
import com.example.controlealimentos.api.service.ProdutoService;

import com.example.controlealimentos.app.controller.AdapterProduto;
import com.example.controlealimentos.app.model.ProdutoDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List_ProdutosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText txtnome;

    Retrofit_URL retrofit = new Retrofit_URL();

    List<ProdutoDTO> listaProdutoDTOS = new ArrayList<>();
    ProdutoService produtoService = retrofit.URLBase().create(ProdutoService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__produtos);
        recyclerView = findViewById(R.id.recyclerProdutos);
        txtnome = findViewById(R.id.txtNome);


        buscarProdutosDisponveis();

        txtnome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override //Evento ao digitar no EditText
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String nome = txtnome.getText().toString();
                buscarProdutosNome(nome);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void buscarProdutosDisponveis() {
        Call<List<ProdutoDTO>> call = produtoService.buscarProdutosDisponiveis();
        call.enqueue(new Callback<List<ProdutoDTO>>() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<ProdutoDTO>> call, Response<List<ProdutoDTO>> response) {
                if (response.isSuccessful()) {
                    listaProdutoDTOS = response.body();
                    listaProdutos(listaProdutoDTOS);
                }
            }

            @Override
            public void onFailure(Call<List<ProdutoDTO>> call, Throwable t) {

            }
        });
    }

    private void buscarProdutosNome(String nome) {
        Call<List<ProdutoDTO>> call = produtoService.buscarProdutosNome(nome);
        call.enqueue(new Callback<List<ProdutoDTO>>() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<ProdutoDTO>> call, Response<List<ProdutoDTO>> response) {
                if (response.isSuccessful()) {
                    listaProdutoDTOS = response.body();

                    if(listaProdutoDTOS.isEmpty()){
                        Toast.makeText(List_ProdutosActivity.this, "PRODUTO NÃO ENCONTRADO", Toast.LENGTH_SHORT).show();
                        buscarProdutosDisponveis();
                    }else{
                        listaProdutos(listaProdutoDTOS);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProdutoDTO>> call, Throwable t) {
                System.out.println("PRODUTO ERRO: "+ t.getMessage());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void listaProdutos(final List<ProdutoDTO> listaProdutoDTOS) {

        for (ProdutoDTO p : listaProdutoDTOS) {
            LocalDate dt = LocalDate.parse(p.getDataValidade());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = dt.format(formatter);

            p.setDataValidade(dataFormatada);
        }

        //CONFIGURAR ADAPTER - PEGA OS DADOS E MONTA UMA VISUALIZAÇÃO
        AdapterProduto adapterProduto = new AdapterProduto(listaProdutoDTOS);

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
                                ProdutoDTO produtoDTO = listaProdutoDTOS.get(position);
                                Toast.makeText(List_ProdutosActivity.this, "CLICK CURTO NOME: " + produtoDTO.getNome(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(List_ProdutosActivity.this, "CLICK CURTO ID: " + produtoDTO.getId(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(List_ProdutosActivity.this, "CLICK CURTO TIPO: " + produtoDTO.getTipo(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                ProdutoDTO produtoDTO = listaProdutoDTOS.get(position);
                                Toast.makeText(List_ProdutosActivity.this, "CLICK LONGO NOME: " + produtoDTO.getNome(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(List_ProdutosActivity.this, "CLICK LONGO TIPO: " + produtoDTO.getTipo(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(List_ProdutosActivity.this, "CLICK LONGO ID: " + produtoDTO.getId(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );
    }

}