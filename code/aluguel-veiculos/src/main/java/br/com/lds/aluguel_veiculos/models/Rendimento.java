package br.com.lds.aluguel_veiculos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
public class Rendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String instituicao;
    private BigDecimal valor;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIgnore 
    private Cliente cliente;
}