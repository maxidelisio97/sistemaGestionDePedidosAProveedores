# Sistema de gestión de pedidos a proveedores.



## Requisitos previos:
 - Java JDK 8
 - MySQL

## Instalación - Desarrollador

 - Clonar el proyecto en NetBeans
 
 - Modificar el archivo src/Modelo/Conexion, con sus datos de usuario, password y host. Por defecto el host es 'localhost', el usuario es 'root' y el password '', colocar password si posee en el string.
```sql
String user = "root";
String password = "";

```
 - Ejecutar el script SQL *db-gestionpedidos.sql* en el servidor MySQL del host.

 - Al ejecutar la aplicación es posible logearse con el usuario *admin* y contraseña *admin1234*
