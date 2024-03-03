package com.example.Testetgid.repository;

import com.example.Testetgid.entites.Empresa;
import com.example.Testetgid.entites.Taxa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxaRepository extends JpaRepository<Taxa, String> {
    List<Taxa> findByEmpresa(Empresa empresa);
}
