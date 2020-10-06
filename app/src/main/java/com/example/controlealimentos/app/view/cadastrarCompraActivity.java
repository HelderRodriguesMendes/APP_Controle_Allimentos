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
import com.example.controlealimentos.api.controller.Retrofit_URL;
import com.example.controlealimentos.api.service.CompraService;
import com.example.controlealimentos.api.service.ProdutoService;
import com.example.controlealimentos.app.controller.ConfigApp;
import com.example.controlealimentos.app.model.CompraDTO;
import com.example.controlealimentos.app.model.ProdutoDTO;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cadastrarCompraActivity extends AppCompatActivity {

    private TextView textViewDataCompra, titulo;
    private EditText txtFoneSupermercado, txtSupermercado;
    private Button btnAddProduto;
    private ImageView imgSalvar;
    int ano, mes, dia;
    String DATA, ultimoCaracterDigitado = "", TITULO, MSG, STATUS;
    static String STATUSFORM;

    CompraDTO COMPRA_DTO = new CompraDTO();

    ConfigApp configAPP = new ConfigApp();
    ConfigApi configApi = new ConfigApi();

    Retrofit_URL retrofit = new Retrofit_URL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar__compra_);

        textViewDataCompra = findViewById(R.id.textViewDataCompra);
        txtFoneSupermercado = findViewById(R.id.txtFoneSupermercado);
        txtSupermercado = findViewById(R.id.txtSupermercado);
        titulo = findViewById(R.id.txtV_Titulo_C);
        btnAddProduto = findViewById(R.id.btnAddProduto);
        imgSalvar = findViewById(R.id.imageViewWSalvar);

        if(STATUSFORM.equals("Alterar")){
            btnAddProduto.setVisibility(View.GONE);
            COMPRA_DTO = getIntent().getExtras().getParcelable("compra");
            preencherCampus(COMPRA_DTO);
            titulo.setText("ALTERAR COMPRA");
        }else{
            imgSalvar.setVisibility(View.GONE);
        }

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
                        textViewDataCompra.setText(configAPP.configDataApp(DATA));
                    }
                },ano,mes,dia);
                datePickerDialog.show();
            }
        });

        imgSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampus()){
                    TITULO = "Alterar Compra";
                    MSG = "Deseja Alterar os Dados?";
                    STATUS = "ALTERAR";
                    msgAlert(TITULO, MSG, STATUS);
                }
            }
        });

        //MASCARA
        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(txtFoneSupermercado, smf);
        txtFoneSupermercado.addTextChangedListener(mtw);

        btnAddProduto.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String vali = txtSupermercado.getText().toString();
                if(validarCampus()){
                    Intent intent = new Intent(cadastrarCompraActivity.this, Cadastrar_Produto_Activity.class);
                    intent.putExtra("compra", preencherObjeto());
                    Cadastrar_Produto_Activity.status_Form("Cadastro de compra");
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

    public void msgAlert(final String titulo, String msg, final String status){
        AlertDialog.Builder builder = new AlertDialog.Builder(cadastrarCompraActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(cadastrarCompraActivity.this).inflate(
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
                alterarCompra(preencherObjeto());
                alertDialog.dismiss();
            }
        });

        view.findViewById(R.id.btnNo).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

            }
        });

        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
    }

    public void alterarCompra(CompraDTO compraDTO){
        CompraService compraService = retrofit.URLBase().create(CompraService.class);
        Call<CompraDTO> call = compraService.atualizar(compraDTO.getId(), compraDTO);

        call.enqueue(new Callback<CompraDTO>() {
            @Override
                public void onResponse(Call<CompraDTO> call, Response<CompraDTO> response) {
                if(response.isSuccessful()){
                    COMPRA_DTO = response.body();
                    TITULO = "ALTERAR Produto";
                    MSG = "Produto alterado com sucesso!";
                    STATUS = "Alterado";
                    limparCampus();
                    msgSucesso(TITULO, MSG, STATUS);
                }
            }

            @Override
            public void onFailure(Call<CompraDTO> call, Throwable t) {
                System.out.println("Erro ao alterar produto: " + t.getMessage());
            }
        });
    }

    public void msgSucesso(String titulo, String msg, final String status){
        AlertDialog.Builder builder = new AlertDialog.Builder(cadastrarCompraActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(cadastrarCompraActivity.this).inflate(
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
                Intent intent = new Intent(cadastrarCompraActivity.this, List_ComprasActivity.class);
                startActivity(intent);
                alertDialog.dismiss();
            }
        });

        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CompraDTO preencherObjeto(){
        CompraDTO c = new CompraDTO();

        if(STATUSFORM.equals("Alterar")){
            c.setId(COMPRA_DTO.getId());
            c.setValorCompra(COMPRA_DTO.getValorCompra());
        }
        String data = textViewDataCompra.getText().toString();
        c.setDataCompra(configApi.configDataApi(data));
        c.setSupermercado(txtSupermercado.getText().toString());
        c.setTelefone(txtFoneSupermercado.getText().toString());

        return c;
    }

    public void limparCampus(){
        textViewDataCompra.setText("");
        txtSupermercado.setText("");
        txtFoneSupermercado.setText("");
    }

    public void preencherCampus(CompraDTO compraDTO){
        textViewDataCompra.setText(compraDTO.getDataCompra());
        txtSupermercado.setText(compraDTO.getSupermercado());
        txtFoneSupermercado.setText(compraDTO.getTelefone());
}

    public static void status_Form(String status){
        STATUSFORM = status;
    }
}