package com.example.controlealimentos.api.model;

import com.example.controlealimentos.app.model.ProdutoAPP;

import java.time.LocalDate;
import java.util.List;

public class CompraAPI {

    private Long id;
    private LocalDate dataCompra;
    private String supermercado;
    private String telefone;
    private Integer valorCompra;
    private int inativo;
    private List<ProdutoAPP> produtos;

    public CompraAPI(){ }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(String supermercado) {
        this.supermercado = supermercado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Integer valorCompra) {
        this.valorCompra = valorCompra;
    }

    public int getInativo() {
        return inativo;
    }

    public void setInativo(int inativo) {
        this.inativo = inativo;
    }

    public List<ProdutoAPP> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoAPP> produtos) {
        this.produtos = produtos;
    }
}
