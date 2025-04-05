package br.com.lds.aluguel_veiculos.controllers;

import br.com.lds.aluguel_veiculos.models.Cliente;
import br.com.lds.aluguel_veiculos.repositories.ClienteRepository;
import br.com.lds.aluguel_veiculos.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

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
    public ResponseEntity<?> cadastrar(@RequestBody Cliente cliente) {
        if (cliente.getRendimentos() != null) {
            cliente.getRendimentos().forEach(r -> r.setCliente(cliente));
        }
        
        try {
            if (clienteRepository.existsByCpf(cliente.getCpf())) {
                return ResponseEntity.badRequest().body("CPF já cadastrado");
            }
            
            if (clienteRepository.existsByRg(cliente.getRg())) {
                return ResponseEntity.badRequest().body("RG já cadastrado");
            }
            
            return ResponseEntity.ok(clienteService.cadastrar(cliente));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao cadastrar cliente");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente existente")
    public ResponseEntity<?> atualizar(
            @PathVariable Integer id, 
            @RequestBody Cliente clienteAtualizado) {
        
        try {
            if (clienteAtualizado.getRendimentos() != null) {
                clienteAtualizado.getRendimentos().forEach(r -> r.setCliente(clienteAtualizado));
            }

            Cliente clienteExistente = clienteRepository.findById(id).orElseThrow();
            
            if (!clienteExistente.getCpf().equals(clienteAtualizado.getCpf())) {
                if (clienteRepository.existsByCpf(clienteAtualizado.getCpf())) {
                    return ResponseEntity.badRequest().body("CPF já cadastrado em outro cliente");
                }
            }
            
            if (!clienteExistente.getRg().equals(clienteAtualizado.getRg())) {
                if (clienteRepository.existsByRg(clienteAtualizado.getRg())) {
                    return ResponseEntity.badRequest().body("RG já cadastrado em outro cliente");
                }
            }
            
            return ResponseEntity.ok(clienteService.atualizar(id, clienteAtualizado));
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar cliente");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir cliente")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}