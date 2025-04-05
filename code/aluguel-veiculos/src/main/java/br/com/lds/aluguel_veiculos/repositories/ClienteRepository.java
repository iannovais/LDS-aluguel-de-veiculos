package br.com.lds.aluguel_veiculos.repositories;

import br.com.lds.aluguel_veiculos.models.Cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByCpfAndSenha(String cpf, String senha);
    Cliente findByCpf(String cpf);
    boolean existsByCpf(String cpf);
    boolean existsByRg(String rg);
}