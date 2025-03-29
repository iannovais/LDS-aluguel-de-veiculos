package br.com.lds.aluguel_veiculos.repositories;

import br.com.lds.aluguel_veiculos.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNome(String nome);
}