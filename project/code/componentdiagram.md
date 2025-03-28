``` code
@startuml

component "Browser" as Browser
component "Spring MVC (Controller)" as Controller
component "Service" as Service
component "Repository" as Repository
component "Database" as Database

Browser --> Controller : HTTP (GET/POST)
Controller --> Service : Lógica de Negócio
Service --> Repository : CRUD
Repository --> Database : SQL/JDBC
@enduml
```