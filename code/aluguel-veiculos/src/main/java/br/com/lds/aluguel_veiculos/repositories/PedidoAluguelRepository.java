package br.com.lds.aluguel_veiculos.repositories;

import br.com.lds.aluguel_veiculos.models.Automovel;
import br.com.lds.aluguel_veiculos.models.PedidoAluguel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoAluguelRepository extends JpaRepository<PedidoAluguel, Integer> {
    List<PedidoAluguel> findAllByAutomovel(Automovel automovel);
}