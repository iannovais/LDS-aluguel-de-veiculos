package br.com.lds.aluguel_veiculos.controllers;

import br.com.lds.aluguel_veiculos.dto.ClienteRequest;
import br.com.lds.aluguel_veiculos.dto.ClienteResponse;
import br.com.lds.aluguel_veiculos.dto.RendimentoRequest;
import br.com.lds.aluguel_veiculos.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clientes")
public class ClienteViewController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "clientes/listar";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovoCliente(Model model) {
        ClienteRequest request = new ClienteRequest();
        model.addAttribute("cliente", request);
        return "clientes/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable Integer id, Model model) {
        ClienteResponse response = clienteService.obterPorId(id);
        ClienteRequest request = new ClienteRequest();

        request.setNome(response.getNome());
        request.setRg(response.getRg());
        request.setCpf(response.getCpf());
        request.setProfissao(response.getProfissao());
        request.setEndereco(response.getEndereco());

        if (response.getRendimentos() != null && !response.getRendimentos().isEmpty()) {
            List<RendimentoRequest> rendimentos = response.getRendimentos().stream()
                    .map(r -> {
                        RendimentoRequest rr = new RendimentoRequest();
                        rr.setInstituicao(r.getInstituicao());
                        rr.setValor(r.getValor());
                        return rr;
                    })
                    .collect(Collectors.toList());
            request.setRendimentos(rendimentos);
        }

        model.addAttribute("cliente", request);
        model.addAttribute("id", id);
        return "clientes/formulario";
    }

    @PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute ClienteRequest cliente) {
        clienteService.cadastrar(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarCliente(@PathVariable Integer id, @ModelAttribute ClienteRequest cliente) {
        clienteService.atualizar(id, cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/excluir/{id}")
    public String excluirCliente(@PathVariable Integer id) {
        clienteService.excluir(id);
        return "redirect:/clientes";
    }
}