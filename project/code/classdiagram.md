```code
@startuml

class Usuario {
    - id : int
    - nome : String
    - senha: String

    + void cadastrar(nome : String, senha : String)
    + void login(nome : String, senha : String)
    + void modificarPedidoAluguel (pedidoAluguel : PedidoAluguel)
}

class Cliente extends Usuario {
    - String rg
    - String cpf
    - String profissao
    - endereco : String

    - List<String> entidadesEmpregadoras
    - List<Double> rendimentos

    + void introduzirPedido()
    + void consultarPedido()
    + void cancelarPedido()
}

class Agente extends Usuario {
    - cnpj : String

    + void analisarPedidoFinanceiro()
    + void executarContrato()
    + void avaliarPedidoAluguel()
    + void vincularAutomovel(automovel : Automovel, usuario : Usuario)
    + void registrarAutomovel(matricula : String, ano : int, marca : String, modelo : String, placa : String)
}


class Banco extends Agente {
    + void concederContratoCredito()
}

class PedidoAluguel {
    - Cliente cliente
    - Automovel automovel
    - Contrato contrato
    - Tipo : TipoPedido
}

enum TipoPedido {
    CREDITO
    A VISTA
}

class Automovel {
    - String matricula
    - int ano
    - String marca
    - String modelo
    - String placa
    - Usuario proprietario

    + void registrar(matricula : String, ano : int, marca : String, modelo : String, placa : String)
}

class Contrato {
    - PedidoAluguel pedido
    - Banco banco
    - boolean aprovado
    - int valorCredito
    + void executar()
}

Cliente "1" -- "*" PedidoAluguel
PedidoAluguel "1" -- "1" Automovel 
PedidoAluguel "1" -- "1" Contrato 
Contrato "0" -- "*" Agente
Automovel "1" -- "1" Usuario 

@enduml
```