package br.com.lds.aluguel_veiculos.dto;

import java.util.List;

public class ClienteResponse {
    private Integer id;
    private String nome;
    private String rg;
    private String cpf;
    private String profissao;
    private String endereco;
    private List<RendimentoResponse> rendimentos;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public List<RendimentoResponse> getRendimentos() {
        return rendimentos;
    }

    public void setRendimentos(List<RendimentoResponse> rendimentos) {
        this.rendimentos = rendimentos;
    }
}