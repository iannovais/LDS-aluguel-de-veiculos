package br.com.lds.aluguel_veiculos.repositories;

import br.com.lds.aluguel_veiculos.models.Automovel;
import br.com.lds.aluguel_veiculos.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AutomovelRepository extends JpaRepository<Automovel, Integer> {
    Optional<Automovel> findByPlaca(String placa);
    boolean existsByPlaca(String placa);
    boolean existsByMatricula(String matricula);
    void deleteByProprietario(Cliente cliente);
}