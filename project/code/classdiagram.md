```code
@startuml

abstract class Usuario {
    - id : int
    - nome : string
    - senha: string

    + cadastrar(nome : string, senha : string) : void 
    + login(nome : string, senha : string) : void 
    + modificarPedidoAluguel (pedidoAluguel : PedidoAluguel) : void 
}

class Rendimento {
    - id : int
    - instituicao : string
    - valor : double
}

class Cliente extends Usuario {
    + MAXRENDIMENTOS : int = 3
    - rg : string 
    - cpf : string 
    - profissao : string 
    - endereco : string

    + introduzirPedido() : void 
    + consultarPedido() : void 
    + cancelarPedido() : void 
}

class Agente extends Usuario {
    - cnpj : string

    + analisarPedidoFinanceiro() : void 
    + executarContrato() : void 
    + avaliarPedidoAluguel() : void 
    + vincularAutomovel(automovel : Automovel, usuario : Usuario) : void 
    + registrarAutomovel(matricula : string, ano : int, marca : string, modelo : string, placa : string) : void 
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
    - matricula : string 
    - ano : int
    - marca : string 
    - modelo : string 
    - placa : string 
    - proprietario : Usuario

    + registrar(matricula : string, ano : int, marca : string, modelo : string, placa : string) : void
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