package br.com.lds.aluguel_veiculos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutomovelDTO {
    @NotBlank(message = "Matrícula é obrigatória")
    @Size(min = 5, max = 20, message = "Matrícula deve ter entre 5 e 20 caracteres")
    private String matricula;
    
    @NotNull(message = "Ano é obrigatório")
    @Min(value = 1886, message = "Ano deve ser maior que 1886")
    @Max(value = 2025, message = "Ano não pode ser no futuro")
    private Integer ano;
    
    @NotBlank(message = "Marca é obrigatória")
    @Size(min = 2, max = 50, message = "Marca deve ter entre 2 e 50 caracteres")
    private String marca;
    
    @NotBlank(message = "Modelo é obrigatório")
    @Size(min = 2, max = 50, message = "Modelo deve ter entre 2 e 50 caracteres")
    private String modelo;
    
    @NotBlank(message = "Placa é obrigatória")
    @Pattern(regexp = "[A-Z]{3}[0-9][A-Z0-9][0-9]{2}", 
             message = "Placa deve estar no formato AAA0A00 ou AAA0000")
    private String placa;
    
    @NotNull(message = "ID do proprietário é obrigatório")
    private Integer proprietarioId;
}