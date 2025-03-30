package br.com.lds.aluguel_veiculos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RendimentoRequest {
    @NotBlank(message = "Instituição é obrigatória")
    private String instituicao;
    
    @NotNull(message = "Valor é obrigatório")
    private BigDecimal valor;

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}