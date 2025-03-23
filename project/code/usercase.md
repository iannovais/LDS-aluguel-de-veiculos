```code

@startuml

left to right direction
actor "Cliente" as cliente
actor "Agente" as agente
actor "Empresa" as empresa
actor "Banco" as banco
actor "Usuario" as usuario

rectangle "Sistema de Aluguel de Automóveis" {
    usuario --> (Cadastrar no sistema)
    usuario --> (Login no sistema)
    usuario --> (Modificar pedido de aluguel)
    usuario --> (Registrar automóvel)

    cliente --> (Introduzir pedido de aluguel)
    cliente --> (Consultar pedido de aluguel)
    cliente --> (Cancelar pedido de aluguel)
    cliente --> (Cadastrar renda)

    agente --> (Avaliar pedido de aluguel)
    agente --> (Executar contrato)
    agente --> (Analisar pedido financeiro)

    banco --> (Conceder contrato de crédito)

    usuario <|-- cliente
    usuario <|-- agente
    agente <|-- empresa
    agente <|-- banco
}

@enduml

```