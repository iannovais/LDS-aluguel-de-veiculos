package br.com.lds.aluguel_veiculos.repositories;

import br.com.lds.aluguel_veiculos.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByCpf(String cpf);
}