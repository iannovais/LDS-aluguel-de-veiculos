package br.com.lds.aluguel_veiculos.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToOne
    @JoinColumn(name = "pedido_id")
    private PedidoAluguel pedido;
    
    private boolean aprovado;
    private double valorCredito;
}