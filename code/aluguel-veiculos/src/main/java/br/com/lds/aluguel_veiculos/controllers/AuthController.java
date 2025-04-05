package br.com.lds.aluguel_veiculos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lds.aluguel_veiculos.services.ClienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final ClienteService clienteService;

    @PostMapping("/login")
    public ResponseEntity<Integer> login(@RequestParam String cpf, 
                                       @RequestParam String senha) {
        return clienteService.findByCpfAndSenha(cpf, senha)
                .map(cliente -> ResponseEntity.ok(cliente.getId()))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
