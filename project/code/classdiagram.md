```code
@startuml

abstract class Usuario {
    - id : int
    - nome : String
    - senha: String

    + cadastrar(nome : String, senha : String) : void 
    + login(nome : String, senha : String) : void 
    + modificarPedidoAluguel (pedidoAluguel : PedidoAluguel) : void 
}

class Rendimento {
    - id : int
    - instituicao : String
    - valor : double
}

class Cliente extends Usuario {
    - rg : String 
    - cpf : String 
    - profissao : String 
    - endereco : String

    + introduzirPedido() : void 
    + consultarPedido() : void 
    + cancelarPedido() : void 
}

class Agente extends Usuario {
    - cnpj : String

    + analisarPedidoFinanceiro() : void 
    + executarContrato() : void 
    + avaliarPedidoAluguel() : void 
    + vincularAutomovel(automovel : Automovel, usuario : Usuario) : void 
    + registrarAutomovel(matricula : String, ano : int, marca : String, modelo : String, placa : String) : void 
}


class Banco extends Agente {
    + concederContratoCredito() : void 
}

class PedidoAluguel {
    - cliente : Cliente 
    - automovel : Automovel 
    - contrato : Contrato 
    - Tipo : TipoPedido
}

enum TipoPedido {
    CREDITO
    A VISTA
}

class Automovel {
    - matricula : String 
    - ano : int
    - marca : String 
    - modelo : String 
    - placa : String 
    - proprietario : Usuario

    + registrar(matricula : String, ano : int, marca : String, modelo : String, placa : String) : void
}

class Contrato {
    - pedido : PedidoAluguel 
    - banco : Banco 
    - aprovado : boolean 
    - valorCredito : double 
    + executar() : void 
}

Cliente "1" -- "*" PedidoAluguel
Cliente "1" -- "3" Rendimento
PedidoAluguel "1" -- "1" Automovel 
PedidoAluguel "1" -- "1" Contrato 
Contrato "0" -- "*" Agente
Automovel "1" -- "1" Usuario 

@enduml
```