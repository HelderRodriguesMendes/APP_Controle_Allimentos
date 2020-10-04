package com.example.controlealimentos.app.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlealimentos.R;
import com.example.controlealimentos.api.controller.Retrofit_URL;
import com.example.controlealimentos.api.service.CompraService;
import com.example.controlealimentos.app.controller.AdapterCompra;
import com.example.controlealimentos.app.controller.RecyclerItemClickListener;
import com.example.controlealimentos.app.model.CompraDTO;
import com.example.controlealimentos.app.model.ProdutoDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List_ComprasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText txtnomeSupermercado;
    Retrofit_URL retrofit = new Retrofit_URL();

    private List<CompraDTO> listaCompra = new ArrayList<>();
    CompraService compraService = retrofit.URLBase().create(CompraService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__compras);

        recyclerView = findViewById(R.id.recyclerViewCompra);
        txtnomeSupermercado = findViewById(R.id.txtNomeSupermercado);

        buscarCompras();

        txtnomeSupermercado.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override //Evento ao digitar no EditText
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String nome = txtnomeSupermercado.getText().toString();
                buscarCompra_Supermercado(nome);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void buscarCompras(){
        Call<List<CompraDTO>> call = compraService.buscarComprasDisponiveis();
        call.enqueue(new Callback<List<CompraDTO>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<CompraDTO>> call, Response<List<CompraDTO>> response) {
                if(response.isSuccessful()){
                    listaCompra = response.body();
                    listarCompra(listaCompra);
                }
            }

            @Override
            public void onFailure(Call<List<CompraDTO>> call, Throwable t) {

            }
        });
    }

    public void buscarCompra_Supermercado(String nome){
        Call<List<CompraDTO>> call = compraService.buscarCompraSupermercado(nome);
        call.enqueue(new Callback<List<CompraDTO>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<CompraDTO>> call, Response<List<CompraDTO>> response) {
                if(response.isSuccessful()){
                    listaCompra = response.body();

                    if(listaCompra.isEmpty()){
                        Toast.makeText(List_ComprasActivity.this, "COMPRA NÃO ENCONTRADA", Toast.LENGTH_SHORT).show();
                        buscarCompras();
                    }else{
                        listarCompra(listaCompra);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CompraDTO>> call, Throwable t) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void listarCompra(final List<CompraDTO> list){
        for(CompraDTO c : list){
            LocalDate dt = LocalDate.parse(c.getDataCompra());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = dt.format(formatter);
            c.setDataCompra(dataFormatada);
        }

        //Configurar adapter
        AdapterCompra adapterCompra = new AdapterCompra(list);


        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()); //Gerenciador de Layout
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterCompra);

        //EVENTO DE CLICK
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                CompraDTO compraDTO = list.get(position);
                                Toast.makeText(List_ComprasActivity.this, "CLICK CURTO Supermercado: " + compraDTO.getSupermercado(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(List_ComprasActivity.this, "CLICK CURTO ID: " + compraDTO.getId(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(List_ComprasActivity.this, "CLICK CURTO DATA COMPRA: " + compraDTO.getDataCompra(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                CompraDTO compraDTO = list.get(position);
                                Toast.makeText(List_ComprasActivity.this, "CLICK LONGO Supermercado: " + compraDTO.getSupermercado(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(List_ComprasActivity.this, "CLICK LONGO ID: " + compraDTO.getId(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(List_ComprasActivity.this, "CLICK LONGO DATA COMPRA: " + compraDTO.getDataCompra(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );
    }
}