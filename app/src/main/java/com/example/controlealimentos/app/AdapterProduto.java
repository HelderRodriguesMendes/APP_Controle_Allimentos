package com.example.controlealimentos.app;

import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlealimentos.R;

//CLASSE QUE FAZ A SUBSTITUIÇÃO DOS DADOS NA LISTA
public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MyViewHolder> {



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

        holder.nome.setText("Nome de Teste");
        holder.dataValidade.setText("Data de Teste");
        holder.marca.setText("Marca de Teste");
        holder.id.setText("10");
    }

    @Override //RETORNA A QUANTIDADE DE ITENS QUE VAI SER EXIBIDOS
    public int getItemCount() {
        return 5;
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

