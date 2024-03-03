package com.example.Testetgid.entites.Enuns;

public enum StatusTransacao {
    CONCLUIDO("concluido"),
    PENDENTE("pendente"),
    CANCELADO("cancelado");

    private String funcao;
    StatusTransacao(String grupo){this.funcao = grupo;}

    public String getTipoTransacao(){
        return funcao;
    }

}
