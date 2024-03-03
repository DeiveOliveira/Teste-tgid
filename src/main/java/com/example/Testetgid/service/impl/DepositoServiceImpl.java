package com.example.Testetgid.service.impl;

import com.example.Testetgid.DTO.TransacaoRequestDTO;
import com.example.Testetgid.DTO.TransacaoResponseDTO;
import com.example.Testetgid.entites.Cliente;
import com.example.Testetgid.entites.Empresa;
import com.example.Testetgid.entites.Enuns.StatusTransacao;
import com.example.Testetgid.entites.Enuns.TipoTaxa;
import com.example.Testetgid.entites.Taxa;
import com.example.Testetgid.entites.Transacao;
import com.example.Testetgid.repository.ClienteRespository;
import com.example.Testetgid.repository.EmpresaRespository;
import com.example.Testetgid.repository.TaxaRepository;
import com.example.Testetgid.repository.TransacaoRepository;
import com.example.Testetgid.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepositoServiceImpl implements TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ClienteRespository clienteRespository;

    @Autowired
    private EmpresaRespository empresaRespository;

    @Autowired
    private TaxaRepository taxaRepository;

    @Override
    public TransacaoResponseDTO fazTransacao(TransacaoRequestDTO transacaoRequestDTO) {
        Cliente cliente = clienteRespository.getReferenceById(transacaoRequestDTO.getClientId());

        Double saldoCliente = cliente.getSaldo() + transacaoRequestDTO.getValor();
        cliente.setSaldo(saldoCliente);
        clienteRespository.save(cliente);

        Empresa empresa = empresaRespository.getReferenceById(transacaoRequestDTO.getEmpresaId());

        List<Taxa> porEmpresa = taxaRepository.findByEmpresa(empresa);

        TransacaoResponseDTO transacaoResponseDTO = porEmpresa.stream()
                .filter(taxa -> taxa.getTipoTaxa().equals(TipoTaxa.DEPOSITO))
                .findFirst()
                .map(taxa -> {
                    Double valorDepositado = transacaoRequestDTO.getValor() - taxa.getValorTaxa();
                    empresa.setSaldo(empresa.getSaldo() + valorDepositado);

                    TransacaoResponseDTO responseDTO = TransacaoResponseDTO.builder()
                            .tipoTransacao(transacaoRequestDTO.getTipoTransacao())
                            .valorTransacao(transacaoRequestDTO.getValor())
                            .dataHora(LocalDateTime.now())
                            .status(StatusTransacao.CONCLUIDO)
                            .descricao("Depósito concluído com sucesso")
                            .valorTaxa(taxa.getValorTaxa())
                            .build();

                    transacaoRepository.save(new Transacao(responseDTO, cliente, empresa));

                    return responseDTO;
                })
                .orElseThrow(() -> new RuntimeException("Taxa de depósito não encontrada para a empresa"));

        return transacaoResponseDTO;
    }
}
