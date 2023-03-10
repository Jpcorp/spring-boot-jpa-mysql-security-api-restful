# spring-boot-jpa-mysql-jwt-api-restful

_Aplicaci贸n API Restful, que expone la creacion de usuarios desarrollada con framerwork spring-boot persistencia a BD Mysql, con autenticaci贸n en JWT._

## Comenzando 馃殌

_El proyecto contiene los siguientes directorios_

- context     : archivos manejo de la configuracion por la aplicaci贸n 
- controllers : encargada de responder a eventos e invocaci贸n de peticiones a las distintas capas de la aplicaci贸n
- domain      : archivos que representan clases centradas en encapsular elementos ligados a representaci贸n de datos
- exceptions  : manejo de excepciones controladas por la aplicaci贸n
- repository  : archivo central estandar para atender consultas a los datos
- security    : archivos para securizar un API Rest esta implementaci贸n con JWT para la generaci贸n de token
- service     : archivos que implemetan la logica de la aplicaci贸n
- validator   : archivos que implemetan validaciones

### Otros directorios 
- htmlReport : Directorio para ver el reporte del cobertura de los fuentes
- assets     : Directorio Resolucion del **Ejercicio 2**, ademas arhivos postman y script de tablas

### Gestion de documental
- La documentacion del proyecto y gestion de las tareas se utilizo la herramienta flying Donuts con una dashboard kamban 
  - Para visualizacion de las tareas https://www.flyingdonut.io/ 
  - las credenciales se enviaran por correo para la visualizaci贸n
 
### Pre-requisitos 馃搵 

_La aplicaci贸n API Restful construida con la herramienta Apache Maven_

```
mvn clean install
```
### Instalaci贸n 馃敡

_Para instalar al aplicaci贸n_

```
mvnw.cmd spring-boot:run
```
Para la ejecuci贸n de la Api Rest.

```
localhost:8080
```
