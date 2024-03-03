package com.example.Testetgid.service;

import com.example.Testetgid.DTO.TransacaoRequestDTO;
import com.example.Testetgid.DTO.TransacaoResponseDTO;

public interface TransacaoService {
    TransacaoResponseDTO fazTransacao(TransacaoRequestDTO transacaoRequestDTO);
}
