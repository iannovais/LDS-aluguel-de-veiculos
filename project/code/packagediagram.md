```code
package configs {}
package controllers {}
package models {}
package repositories {}
package enumerators {}
package views {}
package dto {
    class aluguel {}
    class automovel {}
    class cliente {}
    class usuario {}
    class banco {}
}

controllers ..> configs
controllers ..> repositories
controllers ..> models
controllers ..> dto
controllers ..> views  
models ..> enumerators
```