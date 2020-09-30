package com.example.controlealimentos.app.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlealimentos.R;
import com.example.controlealimentos.app.model.ProdutoDTO;

import java.util.ArrayList;
import java.util.List;

//CLASSE QUE FAZ A SUBSTITUIÇÃO DOS DADOS NA LISTA
public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MyViewHolder> {

    private List<ProdutoDTO> listaprodutos = new ArrayList<>();

    public AdapterProduto(List<ProdutoDTO> lista) {
        listaprodutos = lista;
    }

    @NonNull
    @Override //CRIA (APENAS UMA VEZ) AS VIEWS PARA MOSTRAR PARA O USUARIO
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        //CONVERTER O XML EM VIEW
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista_produto, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override //EXIBE AS VIEWS CRIADAS - EXIBE OS DADOS DA LISTA
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ProdutoDTO produtoDTO = listaprodutos.get(position);

        holder.nome.setText(produtoDTO.getNome());
        holder.dataValidade.setText(produtoDTO.getDataValidade());
        holder.marca.setText(produtoDTO.getMarca());
        holder.id.setText(String.valueOf(produtoDTO.getId()));
    }

    @Override //RETORNA A QUANTIDADE DE ITENS QUE VAI SER EXIBIDOS
    public int getItemCount() {
        return listaprodutos.size();
    }


    //ESSA CLASSE MyViewHolder É UM OBJETO QUE CONFIGURA OS DADOS DA LISTA
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome;              //titulo
        TextView dataValidade;      //ano
        TextView marca;             //genero
        TextView id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textNome);
            dataValidade = itemView.findViewById(R.id.textDataVencimento);
            marca = itemView.findViewById(R.id.textMarca);
            id = itemView.findViewById(R.id.textID);

        }
    }
}

