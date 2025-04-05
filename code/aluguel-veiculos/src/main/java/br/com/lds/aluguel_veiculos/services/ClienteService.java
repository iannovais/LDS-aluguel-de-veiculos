package br.com.lds.aluguel_veiculos.services;

import br.com.lds.aluguel_veiculos.exceptions.ResourceNotFoundException;
import br.com.lds.aluguel_veiculos.models.Cliente;
import br.com.lds.aluguel_veiculos.models.Rendimento;
import br.com.lds.aluguel_veiculos.repositories.AutomovelRepository;
import br.com.lds.aluguel_veiculos.repositories.ClienteRepository;
import br.com.lds.aluguel_veiculos.repositories.RendimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final RendimentoRepository rendimentoRepository;
    private final AutomovelRepository automovelRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findByCpfAndSenha(String cpf, String senha) {
        return clienteRepository.findByCpfAndSenha(cpf, senha);
    }

    public Cliente obterPorId(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente cadastrar(Cliente cliente) {
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new IllegalStateException("CPF já cadastrado");
        }
        if (clienteRepository.existsByRg(cliente.getRg())) {
            throw new IllegalStateException("RG já cadastrado");
        }
        validarRendimentos(cliente.getRendimentos());
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(Integer id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        clienteExistente.setNome(clienteAtualizado.getNome());
        
        if (clienteAtualizado.getSenha() != null && !clienteAtualizado.getSenha().isBlank()) {
            clienteExistente.setSenha(clienteAtualizado.getSenha());
        }
        
        clienteExistente.setRg(clienteAtualizado.getRg());
        clienteExistente.setCpf(clienteAtualizado.getCpf());
        clienteExistente.setProfissao(clienteAtualizado.getProfissao());
        clienteExistente.setEndereco(clienteAtualizado.getEndereco());

        if (clienteAtualizado.getRendimentos() != null) {
            clienteExistente.getRendimentos().clear();

            for (Rendimento rendimento : clienteAtualizado.getRendimentos()) {
                rendimento.setCliente(clienteExistente);
                clienteExistente.getRendimentos().add(rendimento);
            }
        } else {
            clienteExistente.getRendimentos().clear();
        }

        return clienteRepository.save(clienteExistente);
    }

    @Transactional
    public void excluir(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        
        clienteRepository.delete(cliente);
    }

    private void validarRendimentos(List<Rendimento> rendimentos) {
        if (rendimentos != null && rendimentos.size() > Cliente.MAX_RENDIMENTOS) {
            throw new IllegalArgumentException("Limite máximo de " + Cliente.MAX_RENDIMENTOS + " rendimentos");
        }
    }
}