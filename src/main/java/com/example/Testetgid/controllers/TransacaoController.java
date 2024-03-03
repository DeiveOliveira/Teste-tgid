package com.example.Testetgid.controllers;

import com.example.Testetgid.DTO.TransacaoRequestDTO;
import com.example.Testetgid.DTO.TransacaoResponseDTO;
import com.example.Testetgid.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService depositoService;
    private final TransacaoService saqueService;

    @Autowired
    public TransacaoController(@Qualifier("depositoServiceImpl") TransacaoService depositoService,
                               @Qualifier("saqueServiceImpl") TransacaoService saqueService) {
        this.depositoService = depositoService;
        this.saqueService = saqueService;
    }

    @PostMapping("/deposito")
    public ResponseEntity<TransacaoResponseDTO> fazDeposito(@RequestBody TransacaoRequestDTO requestDTO) {
        TransacaoResponseDTO fazTransacao = depositoService.fazTransacao(requestDTO);
        return ResponseEntity.ok(fazTransacao);
    }

    @PostMapping("/saque")
    public ResponseEntity<TransacaoResponseDTO> fazSaque(@RequestBody TransacaoRequestDTO requestDTO) {
        TransacaoResponseDTO fazTransacao = saqueService.fazTransacao(requestDTO);
        return ResponseEntity.ok(fazTransacao);
    }
}
