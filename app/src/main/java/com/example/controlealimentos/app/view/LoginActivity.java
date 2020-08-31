package com.example.controlealimentos.app.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlealimentos.R;
import com.example.controlealimentos.api.UsuarioService;
import com.example.controlealimentos.app.model.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editPassword;
    private Button btnEntrar;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.edit_cad_email);
        editPassword = findViewById(R.id.edit_password);
        btnEntrar = findViewById(R.id.btn_entrar);

        retrofit = new Retrofit.Builder().baseUrl("http:192.168.1.3:8080")
                .addConverterFactory(GsonConverterFactory.create()).build();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                if(!email.equals("") && !password.equals("")){
                    logar();
                }else{
                    Toast.makeText(LoginActivity.this, "Informe Login e Senha", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void logar(){
        UsuarioService usuarioService = retrofit.create(UsuarioService.class);
        Call<Usuario> call = usuarioService.logar();
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    Usuario usuario = response.body();
                    Toast.makeText(LoginActivity.this, usuario.getLogin(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, List_ProdutosActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }
}