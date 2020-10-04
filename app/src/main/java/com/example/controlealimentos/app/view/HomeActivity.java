package com.example.controlealimentos.app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.controlealimentos.R;
import com.example.controlealimentos.app.model.CompraDTO;

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
        Intent intent = new Intent(HomeActivity.this, Cadastrar_Produto_Activity.class);
        switch (view.getId()){
            case R.id.consul_produto:i=new Intent(this, List_ProdutosActivity.class);
            startActivity(i);
            break;
            case R.id.cad_compra:i=new Intent(this, cadastrarCompraActivity.class);
                startActivity(i);
                break;
            case R.id.cad_produto:i=intent;
                CompraDTO compra = new CompraDTO();
                intent.putExtra("compra", compra);
                startActivity(i);
                break;
            case R.id.consul_compra:i=new Intent(this, List_ComprasActivity.class);
                startActivity(i);
                break;
        }

    }
}