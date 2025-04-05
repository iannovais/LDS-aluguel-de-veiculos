package br.com.lds.aluguel_veiculos.controllers;

import br.com.lds.aluguel_veiculos.models.PedidoAluguel;
import br.com.lds.aluguel_veiculos.services.PedidoAluguelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos-aluguel")
@Tag(name = "Pedido Aluguel", description = "API para gerenciamento de pedidos de aluguel")
@RequiredArgsConstructor
public class PedidoAluguelController {
    private final PedidoAluguelService service;

    @GetMapping
    public ResponseEntity<List<PedidoAluguel>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoAluguel> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PedidoAluguel> create(@RequestBody PedidoAluguel pedido) {
        return ResponseEntity.ok(service.save(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}