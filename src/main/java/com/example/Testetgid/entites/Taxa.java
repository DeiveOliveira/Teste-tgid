package com.example.Testetgid.entites;

import com.example.Testetgid.entites.Enuns.TipoTaxa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Taxa")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Taxa {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String taxaId;
    private TipoTaxa tipoTaxa;
    private Double valorTaxa;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "empresaId")
    private Empresa empresa;
}
