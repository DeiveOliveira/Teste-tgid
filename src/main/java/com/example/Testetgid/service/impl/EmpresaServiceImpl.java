package com.example.Testetgid.service.impl;

import com.example.Testetgid.DTO.EmpresaRequestDTO;
import com.example.Testetgid.entites.Empresa;
import com.example.Testetgid.entites.Taxa;
import com.example.Testetgid.repository.EmpresaRespository;
import com.example.Testetgid.repository.TaxaRepository;
import com.example.Testetgid.service.EmpresaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRespository empresaRepository;

    @Autowired
    private TaxaRepository taxaRepository;

    @Override
    public Empresa criarEmpresa(EmpresaRequestDTO empresaRequest) {
        // Criar a empresa
        Empresa novaEmpresa = new Empresa();
        novaEmpresa.setNomeEmpresa(empresaRequest.getNomeEmpresa());
        novaEmpresa.setCnpj(empresaRequest.getCnpj());
        novaEmpresa.setSaldo(empresaRequest.getSaldo());

        // Associar taxas à empresa
        List<Taxa> taxaAssociadas = empresaRequest.getTaxaIds().stream()
                .map(taxaId -> taxaRepository.findById(taxaId)
                        .orElseThrow(() -> new EntityNotFoundException("Taxa com ID " + taxaId + " não encontrada")))
                .collect(Collectors.toList());



        // Salvar a empresa no banco de dados
        Empresa empresa = empresaRepository.save(novaEmpresa);
        taxaAssociadas.forEach( taxa -> {
            taxa.setEmpresa(empresa);
            taxaRepository.save(taxa);
        });
        novaEmpresa.setTaxa(taxaAssociadas);

        return novaEmpresa;

    }
}