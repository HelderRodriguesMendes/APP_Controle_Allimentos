package com.example.controlealimentos.app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.controlealimentos.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView cadCompra, cadProduto, consulCompra, consulProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cadCompra = findViewById(R.id.cad_compra);
        cadProduto = findViewById(R.id.cad_produto);
        consulCompra = findViewById(R.id.consul_compra);
        consulProduto = findViewById(R.id.consul_produto);

        cadCompra.setOnClickListener(this);
        cadProduto.setOnClickListener(this);
        consulCompra.setOnClickListener(this);
        consulProduto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.consul_produto:i=new Intent(this, List_ProdutosActivity.class);
            startActivity(i);
            break;
        }

    }
}