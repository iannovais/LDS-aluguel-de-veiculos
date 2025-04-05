package br.com.lds.aluguel_veiculos.dto;

import br.com.lds.aluguel_veiculos.enums.TipoPedido;
import lombok.Data;

@Data
public class PedidoAluguelDTO {
    private Integer clienteId;
    private Integer automovelId;
    private TipoPedido tipo;
    private Double valorCredito;
}