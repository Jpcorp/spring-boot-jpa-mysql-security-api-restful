# spring-boot-jpa-mysql-jwt-api-restful

_Aplicación API Restful, que expone la creacion de usuarios desarrollada con framerwork spring-boot persistencia a BD Mysql, con autenticación en JWT._

## Comenzando 🚀

_El proyecto contiene los siguientes directorios_

- context     : archivos manejo de la configuracion por la aplicación 
- controllers : encargada de responder a eventos e invocación de peticiones a las distintas capas de la aplicación
- domain      : archivos que representan clases centradas en encapsular elementos ligados a representación de datos
- exceptions  : manejo de excepciones controladas por la aplicación
- repository  : archivo central estandar para atender consultas a los datos
- security    : archivos para securizar un API Rest esta implementación con JWT para la generación de token
- service     : archivos que implemetan la logica de la aplicación
- validator   : archivos que implemetan validaciones

### Otros directorios 
- htmlReport : Directorio para ver el reporte del cobertura de los fuentes
- assets     : Directorio contiene arhivos postman y script de tablas

### Gestion de documental
- La documentacion del proyecto y gestion de las tareas se utilizo la herramienta flying Donuts con una dashboard kamban 
  - Para visualizacion de las tareas https://www.flyingdonut.io/ 
  - las credenciales se enviaran por correo para la visualización
 
### Pre-requisitos 📋 

_La aplicación API Restful construida con la herramienta Apache Maven_

```
mvn clean install
```
### Instalación 🔧

_Para instalar al aplicación_

```
mvnw.cmd spring-boot:run
```
Para la ejecución de la Api Rest.

```
localhost:8080
```
