package com.example.Testetgid.controllers;

import com.example.Testetgid.entites.Cliente;
import com.example.Testetgid.repository.ClienteRespository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRespository clienteRespository;

    public ClienteController(ClienteRespository clienteRespository) {
        this.clienteRespository = clienteRespository;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarUsuario(@RequestBody Cliente cliente) {

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRespository.save(cliente));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarUsuario(@PathVariable String id) {
        Optional<Cliente> cliente = clienteRespository.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarUsuario(@PathVariable String id, @RequestBody Cliente clienteAtualizado) {
        Optional<Cliente> cliente = clienteRespository.findById(id);
        if (cliente.isPresent()) {
            clienteAtualizado.setClienteId(id);
            return ResponseEntity.ok(clienteRespository.save(clienteAtualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String id) {
        Optional<Cliente> cliente = clienteRespository.findById(id);
        if (cliente.isPresent()) {
            clienteRespository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
