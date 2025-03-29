package br.com.lds.aluguel_veiculos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de dados do cliente")
public class ClienteResponse {
    @Schema(description = "ID do cliente", example = "1")
    private Integer id;
    
    @Schema(description = "Nome completo do cliente", example = "João da Silva")
    private String nome;
    
    @Schema(description = "Número do RG", example = "12.345.678-9")
    private String rg;
    
    @Schema(description = "Número do CPF", example = "123.456.789-00")
    private String cpf;
    
    @Schema(description = "Profissão", example = "Engenheiro")
    private String profissao;
    
    @Schema(description = "Endereço completo", example = "Rua das Flores, 123 - Centro - São Paulo/SP")
    private String endereco;

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
}