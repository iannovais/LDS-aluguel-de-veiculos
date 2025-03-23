```
@startuml

class Usuario {
    - id : int
    - nome : String
    - senha: String

    + void cadastrar(nome : String, senha : String)
    + void login(nome : String, senha : String)
    + void modificarPedidoAluguel (pedidoAluguel : PedidoAluguel)
    + void registrarAutomovel(matricula : String, ano : int, marca : String, modelo : String, placa : String)
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

    + void avaliarPedido()
    + void executarContrato()
    + void analisarPedidoFinanceiro()
}

class Empresa extends Agente {}

class Banco extends Agente {
    + void concederContratoCredito()
}

class PedidoAluguel {
    - Cliente cliente
    - Automovel automovel
    - Contrato contrato
    + void modificar()
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
    + void executar()
}

class SistemaAluguel {
    - List<PedidoAluguel> pedidos
    - List<Usuario> usuarios
    - List<Automovel> automoveis
    + void gerenciarPedidos()
    + void construirPaginasWeb()
}

Cliente "1" -- "*" PedidoAluguel : realiza
PedidoAluguel "1" -- "1" Automovel : inclui
PedidoAluguel "1" -- "1" Contrato : gera
Contrato "1" -- "1" Banco : concedidoPor
Automovel "1" -- "1" Usuario : pertenceA
SistemaAluguel "1" -- "*" PedidoAluguel : gerencia
SistemaAluguel "1" -- "*" Usuario : registra
SistemaAluguel "1" -- "*" Automovel : contém

@enduml

```