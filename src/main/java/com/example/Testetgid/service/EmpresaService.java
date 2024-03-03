package com.example.Testetgid.service;

import com.example.Testetgid.DTO.EmpresaRequestDTO;
import com.example.Testetgid.entites.Empresa;

public interface EmpresaService {

    Empresa criarEmpresa(EmpresaRequestDTO empresaRequestDTO);
}
