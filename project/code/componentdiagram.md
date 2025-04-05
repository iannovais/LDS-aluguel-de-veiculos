``` code
@startuml

component "FrontEnd" as Browser
component "Backend" as Backend {
    component "Gerenciamento de cliente" as gCliente
    component "Gerenciamento de banco" as gBanco
    component "Gerenciamento de automovel" as gAutomovel
    component "Gerenciamento de Contrato" as gContrato
    component "Gerenciamento de Pedido de aluguel" as gPAluguel
}
component "Database" as Database {
    component "Agente" as agente
    component "Banco" as banco
    component "Usuario" as cliente
    component "Cliente" as cliente
    component "Automóvel" as automovel
    component "Contrato" as contrato
    component "PedidoAluguel" as pedido
}

Browser --> Backend : HTTPS
Backend --> Database
@enduml
```