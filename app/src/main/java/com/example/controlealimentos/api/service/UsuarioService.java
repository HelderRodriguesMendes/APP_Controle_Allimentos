package com.example.controlealimentos.api;

import com.example.controlealimentos.app.model.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsuarioService {

    @GET("/usuario?login=atentatecnolog&senha=admin")
    Call<Usuario> logar();
}
