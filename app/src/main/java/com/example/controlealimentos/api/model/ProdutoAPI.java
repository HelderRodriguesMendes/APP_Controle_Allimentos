package com.example.controlealimentos.api.model;

import com.example.controlealimentos.app.model.CompraAPP;

import java.time.LocalDate;

public class ProdutoAPI {

    private Long id;
    private String tipo;
    private String nome;
    private String marca;
    private Integer valor;
    private LocalDate dataValidade;
    private Long statusConsumo;
    private CompraAPP compra;

    public ProdutoAPI (){}

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

    public CompraAPP getCompra() {
        return compra;
    }

    public void setCompra(CompraAPP compra) {
        this.compra = compra;
    }
}
