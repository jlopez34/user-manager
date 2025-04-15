# user-manager
API Restful para la creación de usarios.

Aplicación que expone una API RESTful de creación de usuarios.
Todos los endpoints deben aceptar y retornar solamente JSON, inclusive al para los mensajes de error.
Todos los mensajes deben seguir el formato:
```bash
{ "mensaje" : "mensaje de error" }
```
##### Registro

- Ese endpoint deberá recibir un usuario con los campos "nombre", "correo", "contraseña", más
un listado de objetos "teléfono", respetando el siguiente formato:
```bash
{
"name" : "Juan Rodriguez" ,
"email" : " juan@rodriguez.org " ,
"password" : "hunter2" ,
"phones" : [
        {
            "number" : "1234567" ,
            "citycode" : "1" ,
            "contrycode" : "57"
        }
    ]
}
```
- Responder el código de status HTTP adecuado
- En caso de éxito, retorne el usuario y los siguientes campos:  
○ id: id del usuario (puede ser lo que se genera por el banco de datos, pero sería más
deseable un UUID)  
○ created: fecha de creación del usuario   
○ modified: fecha de la última actualización de usuario   
○ last_login: del último ingreso (en caso de nuevo usuario, va a coincidir con la fecha
de creación)  
○ token: token de acceso de la API (puede ser UUID o JWT)  
○ isactive: Indica si el usuario sigue habilitado dentro del sistema.
- Si caso el correo conste en la base de datos, deberá retornar un error "El correo ya
registrado".
- El correo debe seguir una expresión regular para validar que formato sea el correcto.
( aaaaaaa@dominio.cl )
- La clave debe seguir una expresión regular para validar que formato sea el correcto. (Una
Mayuscula, letras minúsculas, y dos números)
- La clave debe seguir una expresión regular para validar que formato sea el correcto. (El
  valor de la expresión regular debe ser configurable)
- El token deberá ser persistido junto con el usuario

## Implementación

### Diagrama de Solución:
#### Flujo de Consumo de servicios:
<img width="864" alt="Screenshot 2025-04-15 at 2 29 05 PM" src="https://github.com/user-attachments/assets/6490709f-5d8e-4dfe-91b6-b39dda8beace" />



Debido a que la implementación fue realizada con una base de datos en memoria (H2), cada vez que se ejecute el projecto
se debe crear el usuario para poder acceder al servicio de creación de perfiles, por lo mismo la secuencia de consumo
de servicios seria el siguiente:  
1. Sign up: Este servicio creará un usuario y retornará el token de acceso para consumir la creación de perfil.
2. Profile create: Se debe utilizar el token obtenido en el paso anterior para ser envidado como authorización para creación de perfiles.
3. Sign in: En caso el token pierda validez se puede obtener un nuevo token utilizando este servicio. 


### EndPoints:
| Metodo Http | Endpoint                                     | Descripción                                       |
|-------------|----------------------------------------------|---------------------------------------------------|
| POST        | http://localhost:8080/api/v1/profile/create/ | Servicio utilizado para la creación de perfiles   |
| POST        | http://localhost:8080/api/auth/signin        | Servicio para la validación de usuarios del App.  |
| POST        | http://localhost:8080/api/auth/signup        | Creación de nuevos usuarios para utilizar la App. |

* Swagger Documentation :  
  http://localhost:8080/swagger-ui/index.html#/

* Postman Collection:
  https://github.com/jlopez34/user-manager/blob/master/env/BCI%20-%20Test.postman_collection.json

### Entrega

* Banco de datos en memoria H2.
* Proceso de build via Maven.
* Persistencia con JPA Hibernate
* Framework SpringBoot  3.4.4
* Java 8+ (17)
* Repositorio publico github y script de creación de BD (Base de Datos en Memoria H2)
* Diagrama de la Solución
* JWT como token
* Pruebas unitarias
* Swagger

### Proxima Entrega

* Mejorar pruebas unitarias realizadas.
* Configuración de la expresión regular para validación del formato del email.


### Estructura 
```
com.smartjob.managing.profile 
  - config  ---> paquete para la configuración WebSecurity y Swagger. 
  - controller --> paquete que expone los servicios Profile y Autenticación.
  - payload --> paquete con el modelo de datos request/response.
  - mapper --> paquete para adaptación de model de negocio/persitencia
  - security --> paquete para la implementación de validación de seguridad.
  - repository --> paquete para manejo de persistencia H2
  - service --> paquethe para la implementación de logia de los diferentes servicios.
ProfileManagerApplication (Main)
```
### Requerimientos

* `Java 17`
* `Maven`
* `Mockito`
* `Spring Boot 3.4.4`
* `H2`

### Build and Tests
#### Build

1. Clonar el projecto [https://github.com/jlopez34/user-manager.git][https://github.com/jlopez34/user-manager.git]
2. Ejecutar `./mvn clean install`
3. Ejecutar `./mvn spring-boot:run`
4. Otra opción es ejecutar la siguiente linea `java -jar target/user-manager-0.0.1-SNAPSHOT.jar`
5. Descargar Postman Collection:[https://github.com/jlopez34/user-manager/blob/master/env/BCI%20-%20Test.postman_collection.json][https://github.com/jlopez34/user-manager/blob/master/env/BCI%20-%20Test.postman_collection.json]

#### Test
Ejecutar `./mvn test` para correr los test unitarios.<br>

[https://github.com/jlopez34/user-manager.git]: https://github.com/jlopez34/user-manager

[https://github.com/jlopez34/user-manager/blob/master/env/BCI%20-%20Test.postman_collection.json]: https://github.com/jlopez34/user-manager/blob/master/env/BCI%20-%20Test.postman_collection.json

