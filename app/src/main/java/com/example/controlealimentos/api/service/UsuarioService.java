package com.example.controlealimentos.api.service;

import com.example.controlealimentos.app.model.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuarioService {

    //@GET("/usuario?login=atentatecnolog&senha=admin")
    @GET("/usuario?login=login&senha=senha")
    Call<Usuario> logar(@Query(value = "login") String email, @Query(value = "senha") String senha);
}
