package com.example.controlealimentos.app.view;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlealimentos.R;
import com.example.controlealimentos.api.controller.Retrofit_URL;
import com.example.controlealimentos.api.service.UsuarioService;
import com.example.controlealimentos.app.model.Usuario;


import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editPassword;
    private Button btnEntrar;

    Retrofit_URL retrofit = new Retrofit_URL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.edit_cad_email);
        editPassword = findViewById(R.id.edit_password);
        btnEntrar = findViewById(R.id.btn_entrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                if(!email.equals("") && !password.equals("")){
                    logar(email, password);
                }else{
                    Toast.makeText(LoginActivity.this, "Informe Login e Senha", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void logar(String email, String senha){
        System.out.println("LOGIN E SENHA: " + email + " " + senha);

        UsuarioService usuarioService = retrofit.URLBase().create(UsuarioService.class);
        Call<Usuario> call = usuarioService.logar(email, senha);
        call.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario usuario = response.body();

                System.out.println("RESPOSTA DA API: " + usuario.getLogin());

                if(usuario.getLogin().equals("Acesso altorizado")){
                    Toast.makeText(LoginActivity.this, usuario.getLogin(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, usuario.getLogin(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "LOGIN FALHOU", Toast.LENGTH_SHORT).show();
                System.out.println("LOGIN FALHOU: " + t.getMessage());
            }
        });
    }

}