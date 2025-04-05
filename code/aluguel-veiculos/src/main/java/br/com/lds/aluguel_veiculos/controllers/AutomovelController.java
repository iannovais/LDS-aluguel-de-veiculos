package br.com.lds.aluguel_veiculos.controllers;

import br.com.lds.aluguel_veiculos.dto.AutomovelDTO;
import br.com.lds.aluguel_veiculos.exceptions.ResourceNotFoundException;
import br.com.lds.aluguel_veiculos.models.Automovel;
import br.com.lds.aluguel_veiculos.models.Cliente;
import br.com.lds.aluguel_veiculos.repositories.AutomovelRepository;
import br.com.lds.aluguel_veiculos.repositories.ClienteRepository;
import br.com.lds.aluguel_veiculos.services.AutomovelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/automoveis")
@Tag(name = "Automóveis", description = "API para gerenciamento de veículos")
@RequiredArgsConstructor
public class AutomovelController {

    private final AutomovelService automovelService;
    private final AutomovelRepository automovelRepository;
    private final ClienteRepository clienteRepository;

    @GetMapping
    @Operation(summary = "Listar todos os automóveis")
    public List<Automovel> listarTodos() {
        return automovelService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter automóvel por ID")
    public ResponseEntity<Automovel> obterPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(automovelService.obterPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo automóvel")
    public ResponseEntity<?> cadastrar(@RequestBody AutomovelDTO automovelDTO) {
        try {
            if (automovelRepository.existsByPlaca(automovelDTO.getPlaca())) {
                return ResponseEntity.badRequest().body("Placa já cadastrada");
            }
            if (automovelRepository.existsByMatricula(automovelDTO.getMatricula())) {
                return ResponseEntity.badRequest().body("Matrícula já cadastrada");
            }

            Automovel automovel = new Automovel();
            automovel.setMatricula(automovelDTO.getMatricula());
            automovel.setAno(automovelDTO.getAno());
            automovel.setMarca(automovelDTO.getMarca());
            automovel.setModelo(automovelDTO.getModelo());
            automovel.setPlaca(automovelDTO.getPlaca());

            Cliente proprietario = clienteRepository.findById(automovelDTO.getProprietarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
            automovel.setProprietario(proprietario);

            return ResponseEntity.ok(automovelService.cadastrar(automovel));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao cadastrar automóvel");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar automóvel existente")
    public ResponseEntity<?> atualizar(
            @PathVariable Integer id,
            @RequestBody Automovel automovelAtualizado) {
        try {
            Automovel automovelExistente = automovelRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Automóvel não encontrado com ID: " + id));

            if (!automovelExistente.getPlaca().equals(automovelAtualizado.getPlaca()) 
                    && automovelRepository.existsByPlaca(automovelAtualizado.getPlaca())) {
                return ResponseEntity.badRequest().body("Placa já cadastrada em outro veículo");
            }

            if (!automovelExistente.getMatricula().equals(automovelAtualizado.getMatricula()) 
                    && automovelRepository.existsByMatricula(automovelAtualizado.getMatricula())) {
                return ResponseEntity.badRequest().body("Matrícula já cadastrada em outro veículo");
            }

            automovelExistente.setAno(automovelAtualizado.getAno());
            automovelExistente.setMarca(automovelAtualizado.getMarca());
            automovelExistente.setModelo(automovelAtualizado.getModelo());
            automovelExistente.setPlaca(automovelAtualizado.getPlaca());
            automovelExistente.setMatricula(automovelAtualizado.getMatricula());

            if (automovelAtualizado.getProprietario() != null 
                    && !automovelExistente.getProprietario().getId().equals(automovelAtualizado.getProprietario().getId())) {
                Cliente novoProprietario = clienteRepository.findById(automovelAtualizado.getProprietario().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
                automovelExistente.setProprietario(novoProprietario);
            }

            Automovel automovelAtualizadoSalvo = automovelRepository.save(automovelExistente);
            return ResponseEntity.ok(automovelAtualizadoSalvo);
            
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar automóvel: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir automóvel")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        automovelService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}