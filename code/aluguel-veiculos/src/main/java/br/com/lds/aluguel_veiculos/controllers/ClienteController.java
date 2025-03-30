package br.com.lds.aluguel_veiculos.controllers;

import br.com.lds.aluguel_veiculos.dto.ClienteRequest;
import br.com.lds.aluguel_veiculos.dto.ClienteResponse;
import br.com.lds.aluguel_veiculos.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Listar todos os clientes")
    public List<ClienteResponse> listarTodos() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter cliente por ID")
    public ResponseEntity<ClienteResponse> obterPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.obterPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo cliente")
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody ClienteRequest request) {
        return ResponseEntity.ok(clienteService.cadastrar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente existente")
    public ResponseEntity<ClienteResponse> atualizar(
            @PathVariable Integer id, @RequestBody ClienteRequest request) {
        return ResponseEntity.ok(clienteService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir cliente")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}