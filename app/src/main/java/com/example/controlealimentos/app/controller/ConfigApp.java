package com.example.controlealimentos.app.controller;

public class ConfigApp {

    //TEMPO DE EXECUÇÃO DA TELA SPLASH
    public static final int TIME_SPLASH = 2 * 1000;

    public String configDataApp(String data){
        String dataFormatada, DIA = "", MES = "", ANO = "";

        int dia, mes, ano;

        String[] dt = data.split("/");

        dia = Integer.parseInt(dt[0]);
        mes = Integer.parseInt(dt[1]);
        ano = Integer.parseInt(dt[2]);

        DIA = String.valueOf(dia);
        MES = String.valueOf(mes);
        ANO = String.valueOf(ano);

        if(dia < 10) {
            DIA = "0" + dt[0];
        }

        if(mes <10) {
            MES = "0" + dt[1];
        }

        dataFormatada = DIA + "/" + MES + "/" + ANO;

        return dataFormatada;
    }
}
