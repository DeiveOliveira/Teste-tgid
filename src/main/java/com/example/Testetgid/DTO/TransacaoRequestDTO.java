package com.example.Testetgid.DTO;

import com.example.Testetgid.entites.Enuns.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransacaoRequestDTO {
    private String clientId;
    private String empresaId;
    private TipoTransacao tipoTransacao;
    private Double valor;
}
