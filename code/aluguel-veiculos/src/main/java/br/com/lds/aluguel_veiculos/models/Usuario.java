package br.com.lds.aluguel_veiculos.models;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Usuario {
    private String nome;
    private String senha;
}