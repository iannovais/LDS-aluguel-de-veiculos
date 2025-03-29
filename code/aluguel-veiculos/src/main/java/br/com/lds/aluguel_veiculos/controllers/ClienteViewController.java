package br.com.lds.aluguel_veiculos.controllers;

import br.com.lds.aluguel_veiculos.dto.ClienteRequest;
import br.com.lds.aluguel_veiculos.dto.ClienteResponse;
import br.com.lds.aluguel_veiculos.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("cliente", new ClienteRequest());
        return "clientes/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable Integer id, Model model) {
        ClienteResponse cliente = clienteService.obterPorId(id);
        ClienteRequest request = new ClienteRequest();
        request.setNome(cliente.getNome());
        request.setRg(cliente.getRg());
        request.setCpf(cliente.getCpf());
        request.setProfissao(cliente.getProfissao());
        request.setEndereco(cliente.getEndereco());
        request.setEntidadesEmpregadoras(cliente.getEntidadesEmpregadoras()); 
        request.setRendimentos(cliente.getRendimentos()); 
        
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