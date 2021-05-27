# SpringbootApiTwitter
Prueba ApiTwitter con Springboot, H2 y Swagger

Se ha creado un microservicio implementando y usando la Api de Twitter con Springboot, H2 y Swagger.

Para comprobar los datos almacenados en memoria H2, usar la url http://localhost:8080/h2-console, los datos de usuario y contraseña estan en el archivo application.properties.

Se ha implementado la Api Swagger no solo para documentar el código si no para hacer mas facil la prueba de las diferentes funcionabilidades del microservicio. Accediento a la la URL http://localhost:8080/swagger-ui.html, accederas a una interfaz de Swagger donde podrás observar los diferentes Controladores/llamadas y entidades. Desde dicha interfaz se puede directamente hacer pruebas.

En el archivo application.properties están las variables para el acceso a la Api de Twitter, no olvidar introducir las vuestras. Por seguridad se han borrado las propias usadas para el desarrollo y pruebas del microservicio.
