package br.com.lds.aluguel_veiculos.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class ClienteRequest {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "Senha é obrigatória")
    private String senha;
    
    @NotBlank(message = "RG é obrigatório")
    private String rg;
    
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;
    
    @NotBlank(message = "Profissão é obrigatória")
    private String profissao;
    
    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;
    
    private List<RendimentoRequest> rendimentos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<RendimentoRequest> getRendimentos() {
        return rendimentos;
    }

    public void setRendimentos(List<RendimentoRequest> rendimentos) {
        this.rendimentos = rendimentos;
    }
}