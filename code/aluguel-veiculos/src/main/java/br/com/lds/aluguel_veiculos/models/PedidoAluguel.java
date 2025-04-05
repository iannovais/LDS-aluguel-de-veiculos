package br.com.lds.aluguel_veiculos.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.lds.aluguel_veiculos.enums.TipoPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
@Entity
public class PedidoAluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "automovel_id")
    @JsonIgnoreProperties("pedidos") 
    private Automovel automovel;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties({"pedidos", "automoveis"}) 
    private Cliente cliente;
    
    @Enumerated(EnumType.STRING)
    private TipoPedido tipo;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Contrato contrato;
}