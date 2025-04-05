package br.com.lds.aluguel_veiculos.models;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @OneToMany(mappedBy = "automovel", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PedidoAluguel> pedidos;
}