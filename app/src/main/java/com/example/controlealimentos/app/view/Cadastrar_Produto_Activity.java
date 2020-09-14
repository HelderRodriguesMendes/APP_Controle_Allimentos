package com.example.controlealimentos.app.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.controlealimentos.R;
import com.example.controlealimentos.app.controller.Config;

import java.text.NumberFormat;
import java.util.Calendar;

public class Cadastrar_Produto_Activity extends AppCompatActivity {

    private EditText txtNome, txtTipo, txtMarca, txtValor;
    private  TextView textViewDataValidade;

    Config config = new Config();

    NumberFormat numberCurrencyFormat = NumberFormat.getCurrencyInstance();

    int ano, mes, dia;
    String DATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar__produto_);

        txtNome = findViewById(R.id.txtNome);
        txtTipo = findViewById(R.id.txtTipo);
        txtMarca = findViewById(R.id.txtMarca);
        txtValor = findViewById(R.id.txtValor);
        textViewDataValidade = findViewById(R.id.textViewDataValidade);


        final Calendar calendar = Calendar.getInstance();

        textViewDataValidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ano = calendar.get(Calendar.YEAR);
                mes = calendar.get(Calendar.MONTH);
                dia = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Cadastrar_Produto_Activity.this,  AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        DATA = i2+"/"+(i1+1)+"/"+i;
                        textViewDataValidade.setText(config.configData(DATA));
                    }
                },ano,mes,dia);
                datePickerDialog.show();
            }
        });
    }
}