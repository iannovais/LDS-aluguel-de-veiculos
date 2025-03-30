package br.com.lds.aluguel_veiculos.repositories;

import br.com.lds.aluguel_veiculos.models.Rendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RendimentoRepository extends JpaRepository<Rendimento, Integer> {
    @Modifying
    @Query("DELETE FROM Rendimento r WHERE r.cliente.id = :clienteId")
    void deleteByClienteId(@Param("clienteId") Integer clienteId);
}