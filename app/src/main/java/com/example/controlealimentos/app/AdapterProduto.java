package com.example.controlealimentos.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlealimentos.R;
import com.example.controlealimentos.app.model.Produto;

import java.util.ArrayList;
import java.util.List;

//CLASSE QUE FAZ A SUBSTITUIÇÃO DOS DADOS NA LISTA
public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MyViewHolder> {

    private List<Produto> listaprodutos = new ArrayList<>();

    public AdapterProduto(List<Produto> lista) {
        listaprodutos = lista;
        for(Produto p : listaprodutos){
            System.out.println("PRODUTO NO ADAPTER: " + p.getNome());
        }
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

        Produto produto = listaprodutos.get(position);

        holder.nome.setText(produto.getNome());
        holder.dataValidade.setText(produto.getDataValidade());
        holder.marca.setText(produto.getMarca());
        holder.id.setText(String.valueOf(produto.getId()));
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

