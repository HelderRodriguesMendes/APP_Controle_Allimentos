package com.example.controlealimentos.app.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.controlealimentos.R;
import com.example.controlealimentos.app.controller.Config;

import java.util.Calendar;

public class cadastrarCompraActivity extends AppCompatActivity {

    private TextView dataCompra;
    private EditText foneSupermercado;
    private Button btnCadastrarCompra;
    int ano, mes, dia;
    String DATA;


    Config config = new Config();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar__compra_);

        dataCompra = findViewById(R.id.textViewDataCompra);
        foneSupermercado = findViewById(R.id.txtFoneSupermercado);



        final Calendar calendar = Calendar.getInstance();

        dataCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ano = calendar.get(Calendar.YEAR);
                mes = calendar.get(Calendar.MONTH);
                dia = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(cadastrarCompraActivity.this,  AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        DATA = i2+"/"+(i1+1)+"/"+i;
                        dataCompra.setText(config.configData(DATA));
                    }
                },ano,mes,dia);
                datePickerDialog.show();
            }
        });
    }
}