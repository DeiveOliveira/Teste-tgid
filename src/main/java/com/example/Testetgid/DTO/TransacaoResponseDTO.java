package com.example.Testetgid.DTO;

import com.example.Testetgid.entites.Enuns.StatusTransacao;
import com.example.Testetgid.entites.Enuns.TipoTransacao;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Builder
public class TransacaoResponseDTO {
    private TipoTransacao tipoTransacao;
    private Double valorTransacao;
    private Double valorTaxa;
    private String descricao;
    private LocalDateTime dataHora;
    private StatusTransacao status;
}
