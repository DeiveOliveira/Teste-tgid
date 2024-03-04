package com.example.Testetgid.service;

import com.example.Testetgid.DTO.TransacaoResponseDTO;

public interface CallbackService {
     void enviarCallbackParaEmpresa(TransacaoResponseDTO responseDTO);

     void enviarNotificacaoParaCliente(TransacaoResponseDTO responseDTO);
}
