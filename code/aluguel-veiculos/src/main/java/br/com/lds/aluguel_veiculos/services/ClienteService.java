package br.com.lds.aluguel_veiculos.services;

import br.com.lds.aluguel_veiculos.dto.ClienteRequest;
import br.com.lds.aluguel_veiculos.dto.ClienteResponse;
import br.com.lds.aluguel_veiculos.exceptions.ResourceNotFoundException;
import br.com.lds.aluguel_veiculos.models.Cliente;
import br.com.lds.aluguel_veiculos.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteResponse> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public ClienteResponse obterPorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + id));
        return convertToResponse(cliente);
    }

    public ClienteResponse cadastrar(ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNome(request.getNome());
        cliente.setSenha(request.getSenha());
        cliente.setRg(request.getRg());
        cliente.setCpf(request.getCpf());
        cliente.setProfissao(request.getProfissao());
        cliente.setEndereco(request.getEndereco());
        cliente.setEntidadesEmpregadoras(request.getEntidadesEmpregadoras());
        cliente.setRendimentos(request.getRendimentos());
        
        Cliente saved = clienteRepository.save(cliente);
        return convertToResponse(saved);
    }

    public ClienteResponse atualizar(Integer id, ClienteRequest request) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(request.getNome());
                    cliente.setSenha(request.getSenha());
                    cliente.setRg(request.getRg());
                    cliente.setCpf(request.getCpf());
                    cliente.setProfissao(request.getProfissao());
                    cliente.setEndereco(request.getEndereco());
                    cliente.setEntidadesEmpregadoras(request.getEntidadesEmpregadoras());
                    cliente.setRendimentos(request.getRendimentos());
                    
                    Cliente updated = clienteRepository.save(cliente);
                    return convertToResponse(updated);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + id));
    }

    public void excluir(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado com o ID: " + id);
        }
        clienteRepository.deleteById(id);
    }

    private ClienteResponse convertToResponse(Cliente cliente) {
        ClienteResponse response = new ClienteResponse();
        response.setId(cliente.getId());
        response.setNome(cliente.getNome());
        response.setRg(cliente.getRg());
        response.setCpf(cliente.getCpf());
        response.setProfissao(cliente.getProfissao());
        response.setEndereco(cliente.getEndereco());
        response.setEntidadesEmpregadoras(cliente.getEntidadesEmpregadoras());
        response.setRendimentos(cliente.getRendimentos());
        return response;
    }
}