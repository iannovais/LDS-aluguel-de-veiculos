```
@startuml

left to right direction

node "Computador do usuário" as computador {
    [Navegador]
}

node "Servidor de aplicação" as servidor {
    [Frontend]
    [Backend]
}

node "Banco de Dados" as bd {
    [Database]
}

[Navegador] ..> [Backend]
[Navegador] ..> [Frontend]
[Frontend] ..> [Backend]
[Backend] ..> [Database]
computador --> servidor : HTTPS
servidor --> bd : Conexão BD

@enduml
```