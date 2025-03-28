```code
@startuml

left to right direction
actor "Cliente" as cliente
actor "Agente" as agente
actor "Banco" as banco
actor "Usuario" as usuario

rectangle "Sistema de Aluguel de Automóveis" {
    usuario --> (Cadastrar no sistema)
    usuario --> (Login no sistema)
    usuario --> (Modificar pedido de aluguel)

    cliente --> (Introduzir pedido de aluguel)
    cliente --> (Consultar pedido de aluguel)
    cliente --> (Cancelar pedido de aluguel)
    cliente --> (Cadastrar renda)

    agente--> (Registrar automóvel)
    agente--> (Vincular automóvel)
    agente --> (Avaliar pedido de aluguel)
    agente --> (Executar contrato)
    agente --> (Analisar pedido financeiro)

    banco --> (Conceder contrato de crédito)
    banco --> (Analisar Pedido Financeiro)


    usuario <|-- cliente
    usuario <|-- agente

    agente <|-- banco
}

@enduml
```