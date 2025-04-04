package br.com.lds.aluguel_veiculos.controllers;

import br.com.lds.aluguel_veiculos.models.Cliente;
import br.com.lds.aluguel_veiculos.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Listar todos os clientes")
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter cliente por ID")
    public ResponseEntity<Cliente> obterPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.obterPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo cliente")
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
        if (cliente.getRendimentos() != null) {
            cliente.getRendimentos().forEach(r -> r.setCliente(cliente));
        }
        return ResponseEntity.ok(clienteService.cadastrar(cliente));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente existente")
    public ResponseEntity<Cliente> atualizar(
            @PathVariable Integer id, 
            @RequestBody Cliente clienteAtualizado) {
        
        if (clienteAtualizado.getRendimentos() != null) {
            clienteAtualizado.getRendimentos().forEach(r -> r.setCliente(clienteAtualizado));
        }
        
        return ResponseEntity.ok(clienteService.atualizar(id, clienteAtualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir cliente")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}