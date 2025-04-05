package br.com.lds.aluguel_veiculos.models;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonIgnore 
    private Cliente proprietario;

    @JsonProperty("proprietarioId")
    public Integer getProprietarioId() {
        return proprietario != null ? proprietario.getId() : null;
    }

    @OneToMany(mappedBy = "automovel", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PedidoAluguel> pedidos;
}