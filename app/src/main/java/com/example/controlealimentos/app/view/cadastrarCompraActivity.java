package com.example.controlealimentos.app.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controlealimentos.R;
import com.example.controlealimentos.app.controller.ConfigApp;
import com.example.controlealimentos.app.model.Compra;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.Calendar;

public class cadastrarCompraActivity extends AppCompatActivity {

    private TextView textViewDataCompra;
    private EditText txtFoneSupermercado, txtSupermercado;
    private Button btnAddProduto;
    int ano, mes, dia;
    String DATA, ultimoCaracterDigitado = "";


    ConfigApp config = new ConfigApp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar__compra_);

        textViewDataCompra = findViewById(R.id.textViewDataCompra);
        txtFoneSupermercado = findViewById(R.id.txtFoneSupermercado);
        txtSupermercado = findViewById(R.id.txtSupermercado);
        btnAddProduto = findViewById(R.id.btnAddProduto);

        final Calendar calendar = Calendar.getInstance();

        textViewDataCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ano = calendar.get(Calendar.YEAR);
                mes = calendar.get(Calendar.MONTH);
                dia = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(cadastrarCompraActivity.this,  AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        DATA = i2+"/"+(i1+1)+"/"+i;
                        textViewDataCompra.setText(config.configDataApp(DATA));
                    }
                },ano,mes,dia);
                datePickerDialog.show();
            }
        });

        //MASCARA
        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(txtFoneSupermercado, smf);
        txtFoneSupermercado.addTextChangedListener(mtw);

        btnAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vali = txtSupermercado.getText().toString();
                if(validarCampus()){
                    Intent intent = new Intent(cadastrarCompraActivity.this, Cadastrar_Produto_Activity.class);
                    intent.putExtra("compra", preencherObjeto());
                    startActivity(intent);
                }
            }
        });
    }

    public boolean validarCampus(){
        boolean ok = false;

        if(textViewDataCompra.getText().toString().equals("")){
            textViewDataCompra.setError("Preenchimento Obrigatório");
            Toast.makeText(cadastrarCompraActivity.this, "INFORME A DATA QUE FOI REALIZADA A COMPRA", Toast.LENGTH_SHORT).show();
        }else if(txtSupermercado.getText().toString().equals("")){
            txtSupermercado.setError("Preenchimento Obrigatório");
            txtSupermercado.requestFocus();
        }else{
            ok = true;
        }
        return ok;
    }

    public Compra preencherObjeto(){
        Compra c = new Compra();

        c.setDataCompra(textViewDataCompra.getText().toString());
        c.setSupermercado(txtSupermercado.getText().toString());
        c.setTelefone(txtFoneSupermercado.getText().toString());

        return c;
    }
}