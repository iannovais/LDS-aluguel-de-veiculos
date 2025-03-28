```code
@startuml

package "Camada Modelo" {
    class PedidoAluguel
    class Contrato
    class Automovel
    class Usuario
    class Cliente
    class Agente
    class Banco
}

package "Camada Controle" {
    class PedidoAluguelController
    class ContratoController
    class AutomovelController
    class UsuarioController
}

package "Camada Visao" {
    class PedidoAluguelView
    class ContratoView
    class AutomovelView
    class UsuarioView
}

Usuario <|-- Cliente
Usuario <|-- Agente
Agente <|-- Banco

Cliente "1" -- "0..*" PedidoAluguel
PedidoAluguel "1" -- "1" Automovel
Automovel "*" -- "1" Usuario
Contrato "1" -- "0..1" Agente
Contrato "1" -- "1" PedidoAluguel
Banco "0..*" -- "1" Cliente

PedidoAluguelController -- PedidoAluguel
ContratoController -- Contrato
AutomovelController -- Automovel
UsuarioController -- Usuario

PedidoAluguelView -- PedidoAluguelController
ContratoView -- ContratoController
AutomovelView -- AutomovelController
UsuarioView -- UsuarioController

@enduml
```