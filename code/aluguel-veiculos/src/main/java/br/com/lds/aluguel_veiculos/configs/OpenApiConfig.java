package br.com.lds.aluguel_veiculos.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API da Matéria LDS-Aluguel de Veículos")
                        .version("1.0")
                        .description("Documentação e especificações da API para o sistema de aluguel de veículos funcionar corretamente"));
    }
}