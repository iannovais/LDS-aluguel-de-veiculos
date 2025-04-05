package br.com.lds.aluguel_veiculos.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Automovel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique = true, nullable = false)
    private String matricula;
    
    @Column(nullable = false)
    private int ano;
    
    @Column(nullable = false)
    private String marca;
    
    @Column(nullable = false)
    private String modelo;
    
    @Column(unique = true, nullable = false)
    private String placa;
    
    @ManyToOne
    @JoinColumn(name = "proprietario_id", nullable = false)
    private Cliente proprietario;
}