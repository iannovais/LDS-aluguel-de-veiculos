package br.com.lds.aluguel_veiculos.services;

import br.com.lds.aluguel_veiculos.enums.TipoPedido;
import br.com.lds.aluguel_veiculos.exceptions.ResourceNotFoundException;
import br.com.lds.aluguel_veiculos.models.*;
import br.com.lds.aluguel_veiculos.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoAluguelService {
    private final PedidoAluguelRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final AutomovelRepository automovelRepository;
    private final ContratoRepository contratoRepository;

@Transactional
public PedidoAluguel criarPedidoCompleto(PedidoAluguel pedido) {
    Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    
    Automovel automovel = automovelRepository.findById(pedido.getAutomovel().getId())
            .orElseThrow(() -> new RuntimeException("Automóvel não encontrado"));

    if (automovel.getProprietario().getId().equals(cliente.getId())) {
        throw new RuntimeException("Cliente não pode alugar veículo de sua própria propriedade");
    }

    pedido.setCliente(cliente);
    pedido.setAutomovel(automovel);

    // Cria contrato automático para pagamento à vista
    if (pedido.getTipo() == TipoPedido.A_VISTA) {
        Contrato contrato = new Contrato();
        contrato.setAprovado(true);
        contrato.setValorCredito(0.0);
        contrato.setPedido(pedido);
        contratoRepository.save(contrato);
    }

    return pedidoRepository.save(pedido);
}

    public List<PedidoAluguel> findAll() {
        return pedidoRepository.findAll(); 
    }

    public PedidoAluguel findById(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(); 
    }

    public PedidoAluguel save(PedidoAluguel pedido) {
        return pedidoRepository.save(pedido); 
    }

    @Transactional
    public void delete(Integer id) {
        PedidoAluguel pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
        
        pedidoRepository.delete(pedido);
    }
}