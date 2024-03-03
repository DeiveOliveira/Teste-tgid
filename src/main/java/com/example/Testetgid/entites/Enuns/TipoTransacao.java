package com.example.Testetgid.entites.Enuns;

public enum TipoTransacao {
    
    DEPOSITO("deposito"),
    SAQUE("saque");

    private String funcao;
    TipoTransacao(String grupo){this.funcao = grupo;}

    public String getTipoTransacao(){
        return funcao;
    }
}
