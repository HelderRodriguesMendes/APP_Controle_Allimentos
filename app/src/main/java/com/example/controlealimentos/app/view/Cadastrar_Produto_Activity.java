package com.example.controlealimentos.app.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controlealimentos.R;
import com.example.controlealimentos.api.controller.ConfigApi;
import com.example.controlealimentos.app.controller.ConfigApp;
import com.example.controlealimentos.app.model.Compra;
import com.example.controlealimentos.app.model.Produto;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Cadastrar_Produto_Activity extends AppCompatActivity {

    private EditText txtNome, txtTipo, txtMarca, txtValor;
    private  TextView textViewDataValidade;
    private ImageView btnAdd, btnSave;
    Compra COMPRA = new Compra();

    boolean cadastrar = true;

    ConfigApp configApp = new ConfigApp();
    ConfigApi configApi = new ConfigApi();

    List<Produto> PRODUTOS = new ArrayList<>();

    NumberFormat numberCurrencyFormat = NumberFormat.getCurrencyInstance();

    int ano, mes, dia;
    String DATA, MSG = "", TITULO = "", STATUS = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar__produto_);

        txtNome = findViewById(R.id.txtNome);
        txtTipo = findViewById(R.id.txtTipo);
        txtMarca = findViewById(R.id.txtMarca);
        txtValor = findViewById(R.id.txtValor);

        textViewDataValidade = findViewById(R.id.textViewDataValidade);
        btnAdd = findViewById(R.id.imageViewAdd);
        btnSave = findViewById(R.id.imageViewSave);

        //recebendo objeto compra mandado pela activity cadastrarCompraActivity
        COMPRA = getIntent().getExtras().getParcelable("compra");
        if(COMPRA.getSupermercado() == null){
            btnAdd.setVisibility(View.GONE);
            cadastrar = false;
        }

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
                        textViewDataValidade.setText(configApp.configDataApp(DATA));
                    }
                },ano,mes,dia);
                datePickerDialog.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(validarCampus()){
                    PRODUTOS.add(preecherObjeto());
                    limparCampus();
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampus()){
                    if(cadastrar){
                        TITULO = "CADASTRAR COMPRA";
                        MSG = "Deseja Finalizar o cadastro da compra?";
                        STATUS = "FINALIZAR COMPRA";
                        msgAlert(TITULO, MSG, STATUS);
                    }else{
                        TITULO = "Alterar";
                        MSG = "Deseja Alterar os Dados?";
                        STATUS = "ALTERAR";
                        msgSucesso(TITULO, MSG, STATUS);
                    }
                }
            }
        });
    }

    public void msgSucesso(String titulo, String msg, final String status){
        AlertDialog.Builder builder = new AlertDialog.Builder(Cadastrar_Produto_Activity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(Cadastrar_Produto_Activity.this).inflate(
                R.layout.layout_success_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.txtTitle)).setText(titulo);
        ((TextView) view.findViewById(R.id.txtMessage)).setText(msg);
        ((Button) view.findViewById(R.id.btnAction)).setText(getResources().getString(R.string.btnOK));
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_success);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btnAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status.equals("FINALIZAR COMPRA")){
                    Intent intent = new Intent(Cadastrar_Produto_Activity.this, HomeActivity.class);
                    startActivity(intent);
                }
                alertDialog.dismiss();
            }
        });

        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
    }

    public void msgAlert(final String titulo, String msg, final String status){
        AlertDialog.Builder builder = new AlertDialog.Builder(Cadastrar_Produto_Activity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(Cadastrar_Produto_Activity.this).inflate(
                R.layout.layout_warning_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.txtTitle)).setText(titulo);
        ((TextView) view.findViewById(R.id.txtMessage)).setText(msg);
        ((Button) view.findViewById(R.id.btnYes)).setText(getResources().getString(R.string.btnYes));
        ((Button) view.findViewById(R.id.btnNo)).setText(getResources().getString(R.string.btnNo));
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_warning);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btnYes).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
               if(status.equals("FINALIZAR COMPRA")){
                   if(!txtTipo.getText().toString().equals("") && !txtNome.getText().toString().equals("") &&
                           !textViewDataValidade.getText().toString().equals("")){
                       if(validarCampus()){
                           PRODUTOS.add(preecherObjeto());

                           //AQUI EU MANDO OS DADOS PARA A API PRA SALVA

                           limparCampus();
                           TITULO = "CADASTRO DE COMPRA";
                           MSG = "Cadastro realizado com sucesso!";
                           STATUS = "FINALIZAR COMPRA";
                           msgSucesso(TITULO, MSG, STATUS);
                           alertDialog.dismiss();
                       }
                   }else{
                       //AQUI EU MANDO OS DADOS PARA A API PRA SALVA

                       TITULO = "CADASTRO DE COMPRA";
                       MSG = "Cadastro realizado com sucesso!";
                       STATUS = "FINALIZAR COMPRA";
                       msgSucesso(TITULO, MSG, STATUS);
                       alertDialog.dismiss();
                   }
               }
            }
        });

        view.findViewById(R.id.btnNo).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(!txtTipo.getText().toString().equals("") && !txtNome.getText().toString().equals("") &&
                        !textViewDataValidade.getText().toString().equals("")){
                    if(validarCampus()){
                        PRODUTOS.add(preecherObjeto());
                        limparCampus();
                        alertDialog.dismiss();
                    }
                }
            }
        });

        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
    }

    public boolean validarCampus(){
        boolean ok = false;

        if(txtTipo.getText().toString().equals("")){
            txtTipo.setError("Preenchimento Obrigatório");
            txtTipo.requestFocus();
        }else if(txtNome.getText().toString().equals("")){
            txtNome.setError("Preenchimento Obrigatório");
            txtNome.requestFocus();
        }else if(txtMarca.getText().toString().equals("")){
            txtMarca.setError("Preenchimento Obrigatório");
            txtMarca.requestFocus();
        }else if(txtValor.getText().toString().equals("")){
            txtValor.setError("Preenchimento Obrigatório");
            txtValor.requestFocus();
        }else if(textViewDataValidade.getText().toString().equals("")){
            textViewDataValidade.setError("Preenchimento Obrigatório");
            Toast.makeText(Cadastrar_Produto_Activity.this, "INFORME A DATA DE VALIDADE DO PRODUTO", Toast.LENGTH_SHORT).show();
        }else{
            ok = true;
        }
        return ok;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Produto preecherObjeto(){
        Produto p = new Produto();

        p.setTipo(txtTipo.getText().toString());
        p.setNome(txtNome.getText().toString());
        p.setMarca(txtMarca.getText().toString());
        String va = txtValor.getText().toString();
        va = va.replace(",", ".");
        p.setValor(Double.parseDouble(va));
        String data = textViewDataValidade.getText().toString();
        p.setDataValidade(configApi.configDataApi(data));

        return p;
    }

    public void limparCampus(){
        txtTipo.setText("");
        txtNome.setText("");
        txtMarca.setText("");
        txtValor.setText("");
        textViewDataValidade.setText("");
    }
}