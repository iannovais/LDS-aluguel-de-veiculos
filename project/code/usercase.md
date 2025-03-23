```code

@startuml

left to right direction

actor Usuario
actor Cliente
actor Agente

Usuario <|-- Cliente
Usuario <|-- Agente

rectangle "Sistema de Aluguel de Automóveis" {
    Usuario --> (Entrar no sistema)
    (Entrar no sistema) .> (Cadastrar no sistema) : include
    Usuario --> (Registrar automóvel)
    Usuario--> (Modificar pedido de aluguel)

    Cliente --> (Introduzir pedido de aluguel)
    Cliente --> (Consultar pedido de aluguel)
    Cliente --> (Cancelar pedido de aluguel)

    Agente --> (Avaliar pedido de aluguel)
    Agente --> (Analisar pedido)
    Agente --> (Executar contrato)
    Agente--> (Associar contrato de crédito)
}

@enduml

```