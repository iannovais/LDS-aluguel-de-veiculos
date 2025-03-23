```code

@startuml

package "Sistema de Aluguel" {
    package "Gestão de Pedidos e Contratos" {
        class PedidoAluguel
        class Contrato
    }
    
    package "Gestão de Automóveis" {
        class Automovel
    }
    
    package "Gestão de Usuários" {
        class Usuario
        class Cliente
        class Agente
        class Banco
        class Empresa
    }
    
    package "Construção Dinâmica de Páginas Web" {
        package "MVC" {
            class Modelo
            class Visao
            class Controle
        }
    }
    
    package "Infraestrutura" {
        class ServidorCentral
        class ComputadoresLocais
    }
}

Usuario <|-- Cliente
Usuario <|-- Agente
Agente <|-- Banco
Agente <|-- Empresa

Cliente "1" -- "0..*" PedidoAluguel
PedidoAluguel "1" -- "1" Automovel
Automovel "*" -- "1" Usuario
Contrato "1" -- "0..1" Agente
Contrato "1" -- "1" PedidoAluguel
Banco "0..*" -- "1" Cliente

ServidorCentral -- ComputadoresLocais : Conexão via Internet

Modelo -- Controle
Controle -- Visao
Visao -- Modelo

@enduml

```