package br.com.lds.aluguel_veiculos.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Entity
public class Cliente extends Usuario {
    public static final int MAX_RENDIMENTOS = 3;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String rg;
    private String cpf;
    private String profissao;
    private String endereco;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rendimento> rendimentos;
}