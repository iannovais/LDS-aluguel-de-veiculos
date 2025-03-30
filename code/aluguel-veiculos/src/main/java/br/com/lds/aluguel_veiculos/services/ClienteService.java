package br.com.lds.aluguel_veiculos.services;

import br.com.lds.aluguel_veiculos.dto.*;
import br.com.lds.aluguel_veiculos.exceptions.ResourceNotFoundException;
import br.com.lds.aluguel_veiculos.models.Cliente;
import br.com.lds.aluguel_veiculos.models.Rendimento;
import br.com.lds.aluguel_veiculos.repositories.ClienteRepository;
import br.com.lds.aluguel_veiculos.repositories.RendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private static final int MAX_RENDIMENTOS = 3;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RendimentoRepository rendimentoRepository;

    public List<ClienteResponse> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public ClienteResponse obterPorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        return convertToResponse(cliente);
    }

    @Transactional
    public ClienteResponse cadastrar(ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNome(request.getNome());
        cliente.setSenha(request.getSenha());
        cliente.setRg(request.getRg());
        cliente.setCpf(request.getCpf());
        cliente.setProfissao(request.getProfissao());
        cliente.setEndereco(request.getEndereco());

        Cliente clienteSalvo = clienteRepository.save(cliente);

        if (request.getRendimentos() != null && !request.getRendimentos().isEmpty()) {
            validarRendimentos(request.getRendimentos()); 
            salvarRendimentos(clienteSalvo, request.getRendimentos());
        }

        return convertToResponse(clienteSalvo);
    }

    @Transactional
    public ClienteResponse atualizar(Integer id, ClienteRequest request) {
        validarRendimentos(request.getRendimentos());

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        cliente.setNome(request.getNome());
        cliente.setSenha(request.getSenha());
        cliente.setRg(request.getRg());
        cliente.setCpf(request.getCpf());
        cliente.setProfissao(request.getProfissao());
        cliente.setEndereco(request.getEndereco());

        rendimentoRepository.deleteByClienteId(cliente.getId());

        if (request.getRendimentos() != null) {
            salvarRendimentos(cliente, request.getRendimentos());
        }

        Cliente updated = clienteRepository.save(cliente);
        return convertToResponse(updated);
    }

    @Transactional
    public void excluir(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
        rendimentoRepository.deleteByClienteId(id);
        clienteRepository.deleteById(id);
    }

    private void validarRendimentos(List<RendimentoRequest> rendimentos) {
        if (rendimentos != null) {
            long count = rendimentos.stream()
                .filter(req -> req.getInstituicao() != null && !req.getInstituicao().isEmpty() 
                            && req.getValor() != null)
                .count();
                
            if (count > 3) {
                throw new IllegalArgumentException("Limite máximo de 3 rendimentos válidos");
            }
        }
    }

    private void salvarRendimentos(Cliente cliente, List<RendimentoRequest> rendimentosRequests) {
        if (rendimentosRequests == null) {
            return;
        }
    
        List<Rendimento> rendimentos = rendimentosRequests.stream()
                .filter(req -> req.getInstituicao() != null && !req.getInstituicao().isEmpty() && req.getValor() != null)
                .map(req -> {
                    Rendimento rendimento = new Rendimento();
                    rendimento.setInstituicao(req.getInstituicao());
                    rendimento.setValor(req.getValor());
                    rendimento.setCliente(cliente);
                    return rendimento;
                })
                .collect(Collectors.toList());
    
        rendimentoRepository.saveAll(rendimentos);
    }

    private ClienteResponse convertToResponse(Cliente cliente) {
        ClienteResponse response = new ClienteResponse();
        response.setId(cliente.getId());
        response.setNome(cliente.getNome());
        response.setRg(cliente.getRg());
        response.setCpf(cliente.getCpf());
        response.setProfissao(cliente.getProfissao());
        response.setEndereco(cliente.getEndereco());

        if (cliente.getRendimentos() != null) {
            response.setRendimentos(cliente.getRendimentos().stream()
                    .map(this::convertToRendimentoResponse)
                    .collect(Collectors.toList()));
        }

        return response;
    }

    private RendimentoResponse convertToRendimentoResponse(Rendimento rendimento) {
        RendimentoResponse response = new RendimentoResponse();
        response.setId(rendimento.getId());
        response.setInstituicao(rendimento.getInstituicao());
        response.setValor(rendimento.getValor());
        return response;
    }
}