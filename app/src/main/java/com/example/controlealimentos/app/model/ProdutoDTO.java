package com.example.controlealimentos.app.model;

public class ProdutoDTO {
    private Long id;
    private String tipo;
    private String nome;
    private String marca;
    private Double valor;
    private String dataValidade;
    private Long statusConsumo;
    private CompraDTO compra;

    public ProdutoDTO() {

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDataValidade() {

        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {


        this.dataValidade = dataValidade;
    }

    public Long getStatusConsumo() {
        return statusConsumo;
    }

    public void setStatusConsumo(Long statusConsumo) {
        this.statusConsumo = statusConsumo;
    }
}