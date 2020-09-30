package com.example.controlealimentos.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


public class CompraDTO implements Parcelable {
    private Long id;
    private String dataCompra;
    private String supermercado;
    private String telefone;
    private Double valorCompra;
    private int inativo;
    private List<ProdutoDTO> produtosDTO= new ArrayList<>();

    public CompraDTO() {

    }

    protected CompraDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        dataCompra = in.readString();
        supermercado = in.readString();
        telefone = in.readString();
        if (in.readByte() == 0) {
            valorCompra = null;
        } else {
            valorCompra = in.readDouble();
        }
        inativo = in.readInt();
    }

    public static final Creator<CompraDTO> CREATOR = new Creator<CompraDTO>() {
        @Override
        public CompraDTO createFromParcel(Parcel in) {
            return new CompraDTO(in);
        }

        @Override
        public CompraDTO[] newArray(int size) {
            return new CompraDTO[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
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

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public int getInativo() {
        return inativo;
    }

    public void setInativo(int inativo) {
        this.inativo = inativo;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtosDTO;
    }

    public void setProdutos(List<ProdutoDTO> produtoDTOS) {
        this.produtosDTO = produtoDTOS;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(dataCompra);
        parcel.writeString(supermercado);
        parcel.writeString(telefone);
        if (valorCompra == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(valorCompra);
        }
        parcel.writeInt(inativo);
    }
}
