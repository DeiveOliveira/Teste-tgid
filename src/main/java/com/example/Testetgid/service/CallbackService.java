package com.example.Testetgid.service;

import com.example.Testetgid.DTO.TransacaoResponseDTO;

public interface CallbackService {

     void enviarNotificacaoParaCliente(TransacaoResponseDTO responseDTO);
}
