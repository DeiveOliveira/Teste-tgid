package com.example.Testetgid.entites.Enuns;

public enum TipoTaxa {
    DEPOSITO("deposito"),
    SAQUE("saque");

    private String funcao;
    TipoTaxa (String grupo){this.funcao = grupo;}

    public String getTipoTransacao(){
        return funcao;
    }
}
