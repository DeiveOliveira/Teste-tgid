package com.example.Testetgid.controllers;

import com.example.Testetgid.entites.Taxa;
import com.example.Testetgid.repository.TaxaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/taxa")
public class TaxaController {

    @Autowired
    private TaxaRepository taxasRepository;

    @PostMapping
    public ResponseEntity<Taxa> criarTaxa(@RequestBody Taxa taxa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taxasRepository.save(taxa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taxa> buscarTaxa(@PathVariable String id) {
        Optional<Taxa> taxas = taxasRepository.findById(id);
        return taxas.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Taxa> atualizarTaxa(@PathVariable String id, @RequestBody Taxa taxaAtualizadas) {
        Optional<Taxa> taxas = taxasRepository.findById(id);
        if (taxas.isPresent()) {
            taxaAtualizadas.setTaxaId(id);
            return ResponseEntity.ok(taxasRepository.save(taxaAtualizadas));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTaxa(@PathVariable String id) {
        Optional<Taxa> taxas = taxasRepository.findById(id);
        if (taxas.isPresent()) {
            taxasRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
