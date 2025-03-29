package br.com.lds.aluguel_veiculos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ClienteRequest {
    @Schema(description = "Nome completo do cliente", example = "João da Silva")
    private String nome;
    
    @Schema(description = "Senha de acesso", example = "senha123")
    private String senha;
    
    @Schema(description = "Número do RG", example = "12.345.678-9")
    private String rg;
    
    @Schema(description = "Número do CPF", example = "123.456.789-00")
    private String cpf;
    
    @Schema(description = "Profissão", example = "Engenheiro")
    private String profissao;
    
    @Schema(description = "Endereço completo", example = "Rua das Flores, 123 - Centro - São Paulo/SP")
    private String endereco;
    
    @Schema(description = "Entidades empregadoras separadas por vírgula", example = "Empresa A, Empresa B")
    private String entidadesEmpregadoras;
    
    @Schema(description = "Rendimentos separados por vírgula", example = "5000.00,3000.50")
    private String rendimentos;


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

    public String getEntidadesEmpregadoras() {
        return entidadesEmpregadoras;
    }

    public void setEntidadesEmpregadoras(String entidadesEmpregadoras) {
        this.entidadesEmpregadoras = entidadesEmpregadoras;
    }

    public String getRendimentos() {
        return rendimentos;
    }

    public void setRendimentos(String rendimentos) {
        this.rendimentos = rendimentos;
    }
}