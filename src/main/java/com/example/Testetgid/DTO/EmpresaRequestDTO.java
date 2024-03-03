package com.example.Testetgid.DTO;
import lombok.Data;

import java.util.List;


@Data
public class EmpresaRequestDTO {
    private String nomeEmpresa;
    private String cnpj;
    private Double saldo;

    private List<String> taxaIds;
}
