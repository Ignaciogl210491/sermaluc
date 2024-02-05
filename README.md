El siguiente proyecto es un reto técnico, el cual consiste:

Desarrollar una aplicación que exponga una API RESTful de creación de usuarios

Registro:

● Ese endpoint deberá recibir un usuario con los campos "nombre", "correo", "contraseña", más un listado de objetos "teléfono", respetando el siguiente formato:
            {
            "name": "Juan Rodriguez",
            "email": "juan@rodriguez.org",
            "password": "hunter2",
            "phones": [
                        {
                        "number": "1234567",
                        "citycode": "1",
                        "contrycode": "57"
                        }
                    ]
            }
● Responder el código de status HTTP adecuado
● En caso de éxito, retorne el usuario y los siguientes campos:

    ○ id: id del usuario (puede ser lo que se genera por el banco de datos, pero sería más deseable un UUID)
    ○ created: fecha de creación del usuario
    ○ modified: fecha de la última actualización de usuario
    ○ last_login: del último ingreso (en caso de nuevo usuario, va a coincidir con la fecha de creación)
    ○ token: token de acceso de la API (puede ser UUID o JWT)
    ○ isactive: Indica si el usuario sigue habilitado dentro del sistema.
● Si caso el correo conste en la base de datos, deberá retornar un error "El correo ya registrado".
● El correo debe seguir una expresión regular para validar que formato sea el correcto.
    (aaaaaaa@dominio.cl)
● La clave debe seguir una expresión regular para validar que formato sea el correcto. (El valor de la expresión regular debe ser configurable)

Solución del reto:

1. Instalar los siguiente en su maquina local:
   Configurar el framework con java 11
   Configurar un maven 3+
   
2. Clonar el proyecto desde la pc, con el siguiente comando:
   git clone https://github.com/Ignaciogl210491/sermaluc.git
   
3. Una vez bajado el proyecto, usar eclipse o intellij para importar el proyecto
   
4. Ejecutar el sigueinte comando en el terminal o consola
   mvn clean install
   
5. La aplicación estará disponible en http://localhost:8082 (puede modificar el puerto de tener conflicto en el application.properties del proyecto)
   
6. Desde swagger:
   http://localhost:8082/swagger-ui/index.html#/

7. Diagrama de Solución
   ![image](https://github.com/Ignaciogl210491/sermaluc/assets/126305939/e07307e2-2d39-42c7-abb6-7e9a7fe296df)

8. Diagrama de Flujo
   ![image](https://github.com/Ignaciogl210491/sermaluc/assets/126305939/5a0ae0a1-c038-405e-bd1c-f6130411926b)

9. Documento Funcional
    [DOCUMENTO FUNCIONAL.docx](https://github.com/Ignaciogl210491/sermaluc/files/14168736/DOCUMENTO.FUNCIONAL.docx)

