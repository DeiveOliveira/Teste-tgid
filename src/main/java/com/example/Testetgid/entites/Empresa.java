package com.example.Testetgid.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Empresa")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String empresaId;
    private String nomeEmpresa;

    @Column(unique = true)
    @CNPJ
    private String cnpj;
    private Double saldo;


    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Taxa> taxa;
}
