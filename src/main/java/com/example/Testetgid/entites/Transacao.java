package com.example.Testetgid.entites;

import com.example.Testetgid.DTO.TransacaoResponseDTO;
import com.example.Testetgid.entites.Enuns.StatusTransacao;
import com.example.Testetgid.entites.Enuns.TipoTransacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Table(name = "transacao")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String trasacaoId;
    private LocalDateTime data;
    private Double valorTransacao;
    private Double valorTaxa;
    private TipoTransacao tipoTransacao;
    private String descricao;
    private StatusTransacao status;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Empresa empresa;

    public Transacao(TransacaoResponseDTO response, Cliente cliente, Empresa empresa) {
        this.data = response.getDataHora();
        this.valorTransacao = response.getValorTransacao();
        this.valorTaxa = response.getValorTaxa();
        this.tipoTransacao = response.getTipoTransacao();
        this.descricao = response.getDescricao();
        this.status = response.getStatus();
        this.cliente = cliente;
        this.empresa = empresa;
    }


}
