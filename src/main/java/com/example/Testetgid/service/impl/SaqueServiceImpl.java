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
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaqueServiceImpl implements TransacaoService {

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

        if (transacaoRequestDTO.getValor() > cliente.getSaldo()) {
            return TransacaoResponseDTO.builder()
                    .tipoTransacao(transacaoRequestDTO.getTipoTransacao())
                    .valorTransacao(transacaoRequestDTO.getValor())
                    .dataHora(LocalDateTime.now())
                    .status(StatusTransacao.CANCELADO)
                    .descricao("Cliente não possui saldo para saque!")
                    .build();
        }

        Double saldoCliente = cliente.getSaldo() - transacaoRequestDTO.getValor();
        cliente.setSaldo(saldoCliente);
        clienteRespository.save(cliente);

        Empresa empresa = empresaRespository.getReferenceById(transacaoRequestDTO.getEmpresaId());

        List<Taxa> porEmpresa = taxaRepository.findByEmpresa(empresa);

        TransacaoResponseDTO transacaoResponseDTO = porEmpresa.stream()
                .filter(taxa -> taxa.getTipoTaxa().equals(TipoTaxa.SAQUE))
                .findFirst()
                .map(taxa -> {
                    Double valorTotalSaque = transacaoRequestDTO.getValor() + taxa.getValorTaxa();

                    if (empresa.getSaldo() >= valorTotalSaque) {
                        empresa.setSaldo(empresa.getSaldo() - valorTotalSaque);

                        TransacaoResponseDTO responseDTO = TransacaoResponseDTO.builder()
                                .tipoTransacao(transacaoRequestDTO.getTipoTransacao())
                                .valorTransacao(transacaoRequestDTO.getValor())
                                .dataHora(LocalDateTime.now())
                                .status(StatusTransacao.CONCLUIDO)
                                .descricao("Saque concluído com sucesso")
                                .valorTaxa(taxa.getValorTaxa())
                                .build();

                        transacaoRepository.save(new Transacao(responseDTO, cliente, empresa));

                        return responseDTO;
                    } else {
                        throw new RuntimeException("Saldo insuficiente para realizar o saque");
                    }
                })
                .orElseThrow(() -> new RuntimeException("Taxa de saque não encontrada para a empresa"));

        return transacaoResponseDTO;
    }
}
