package br.com.lds.aluguel_veiculos.models;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Usuario {
    private String nome;
    private String senha;


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
}
