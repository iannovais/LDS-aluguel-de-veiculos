package br.com.lds.aluguel_veiculos.controllers;

import br.com.lds.aluguel_veiculos.services.UsuarioService;
import br.com.lds.aluguel_veiculos.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastrar")
    public Usuario cadastrar(@RequestParam String nome, @RequestParam String senha) {
        return service.cadastrar(nome, senha);
    }

    @PostMapping("/login")
    public String login(@RequestParam String nome, @RequestParam String senha) {
        return service.login(nome, senha) ? "Login bem-sucedido!" : "Credenciais inválidas";
    }
}