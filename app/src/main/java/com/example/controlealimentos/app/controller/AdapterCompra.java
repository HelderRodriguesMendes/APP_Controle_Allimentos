package com.example.controlealimentos.app.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlealimentos.R;
import com.example.controlealimentos.app.model.CompraDTO;

import java.util.ArrayList;
import java.util.List;

public class AdapterCompra extends RecyclerView.Adapter<AdapterCompra.MyViewHolder> {

    private List<CompraDTO> listaCompra = new ArrayList<>();

    public AdapterCompra(List<CompraDTO> lista){
        listaCompra = lista;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista_compras, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CompraDTO compraDTO = listaCompra.get(position);

        holder.nomeSupermercado.setText(compraDTO.getSupermercado());
        String v = "Valor: R$ " + String.valueOf(compraDTO.getValorCompra());
        holder.valorCompra.setText(v);
        holder.dataCompra.setText(compraDTO.getDataCompra());
    }

    @Override
    public int getItemCount() {
        return listaCompra.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nomeSupermercado;
        TextView valorCompra;
        TextView dataCompra;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeSupermercado = itemView.findViewById(R.id.textNome_Supermercado);
            valorCompra = itemView.findViewById(R.id.textValorCompra);
            dataCompra = itemView.findViewById(R.id.textDataCompra);
        }
    }
}
