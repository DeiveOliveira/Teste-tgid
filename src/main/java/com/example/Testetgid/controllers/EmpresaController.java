package com.example.Testetgid.controllers;

import com.example.Testetgid.DTO.EmpresaRequestDTO;
import com.example.Testetgid.entites.Empresa;
import com.example.Testetgid.repository.EmpresaRespository;
import com.example.Testetgid.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaRespository empresaRespository;

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<Empresa> cadastrarEmpresa(@RequestBody EmpresaRequestDTO empresa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.criarEmpresa(empresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarEmpresa(@PathVariable String id) {
        Optional<Empresa> empresa = empresaRespository.findById(id);
        return empresa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable String id, @RequestBody Empresa empresaAtualizada) {
        Optional<Empresa> empresa = empresaRespository.findById(id);
        if (empresa.isPresent()) {
            empresaAtualizada.setEmpresaId(id);
            return ResponseEntity.ok(empresaRespository.save(empresaAtualizada));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable String id) {
        Optional<Empresa> empresa = empresaRespository.findById(id);
        if (empresa.isPresent()) {
            empresaRespository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
