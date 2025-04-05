package br.com.lds.aluguel_veiculos.repositories;

import br.com.lds.aluguel_veiculos.models.Contrato;
import br.com.lds.aluguel_veiculos.models.PedidoAluguel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
    void deleteByPedido(PedidoAluguel pedido);
}