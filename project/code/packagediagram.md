```code
package configs {}
package controllers {}
package models {}
package repositories {}
package enumerators {}
package views {}
package dto {
    package aluguel {}
    package automovel {}
    package cliente {}
    package usuario {}
    package banco {}
}

controllers ..> configs
controllers ..> repositories
controllers ..> models
controllers ..> dto
controllers ..> views  
models ..> enumerators
```