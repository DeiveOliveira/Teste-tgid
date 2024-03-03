package com.example.Testetgid.entites;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Table(name = "cliente")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String clienteId;
    private String nomeClinte;
    @CPF
    @Column(unique = true)
    private String cpf;
    private Double saldo;

}
