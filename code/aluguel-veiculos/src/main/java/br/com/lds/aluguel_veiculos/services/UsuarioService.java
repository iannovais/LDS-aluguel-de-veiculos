package br.com.lds.aluguel_veiculos.services;

import br.com.lds.aluguel_veiculos.models.Usuario;
import br.com.lds.aluguel_veiculos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario cadastrar(String nome, String senha) {
        Usuario usuario = new Usuario(nome, senha);
        return repository.save(usuario);
    }

    public boolean login(String nome, String senha) {
        Optional<Usuario> usuarioOpt = repository.findByNome(nome);
        return usuarioOpt.map(usuario -> usuario.getSenha().equals(senha)).orElse(false);
    }
} 