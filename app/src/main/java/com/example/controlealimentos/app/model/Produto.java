package com.example.controlealimentos.app.model;

import java.time.LocalDate;

public class Produto {
    private Long id;
    private String tipo;
    private String nome;
    private String marca;
    private Integer valor;
    private LocalDate dataValidade;
    private Long statusConsumo;
    private Long IDcompra;

    public Produto() {

    }

    public Produto(Long id, String tipo, String nome, String marca, Integer valor,
                   LocalDate dataValidade, Long statusConsumo, Long IDcompra) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
        this.dataValidade = dataValidade;
        this.statusConsumo = statusConsumo;
        this.IDcompra = IDcompra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public Long getIDcompra() {
        return IDcompra;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Long getStatusConsumo() {
        return statusConsumo;
    }

    public void setStatusConsumo(Long statusConsumo) {
        this.statusConsumo = statusConsumo;
    }

    public void setIDcompra(Long IDcompra) {
        this.IDcompra = IDcompra;
    }
}