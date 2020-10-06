package com.example.controlealimentos.app.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProdutoDTO implements Parcelable {
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

    protected ProdutoDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        tipo = in.readString();
        nome = in.readString();
        marca = in.readString();
        if (in.readByte() == 0) {
            valor = null;
        } else {
            valor = in.readDouble();
        }
        dataValidade = in.readString();
        if (in.readByte() == 0) {
            statusConsumo = null;
        } else {
            statusConsumo = in.readLong();
        }
        compra = in.readParcelable(CompraDTO.class.getClassLoader());
    }

    public static final Creator<ProdutoDTO> CREATOR = new Creator<ProdutoDTO>() {
        @Override
        public ProdutoDTO createFromParcel(Parcel in) {
            return new ProdutoDTO(in);
        }

        @Override
        public ProdutoDTO[] newArray(int size) {
            return new ProdutoDTO[size];
        }
    };

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
        parcel.writeString(tipo);
        parcel.writeString(nome);
        parcel.writeString(marca);
        if (valor == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(valor);
        }
        parcel.writeString(dataValidade);
        if (statusConsumo == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(statusConsumo);
        }
        parcel.writeParcelable(compra, i);
    }
}
