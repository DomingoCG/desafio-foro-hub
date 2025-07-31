# 📚 Foro Alura - API REST

Este es un proyecto de una API REST construida con **Spring Boot 3**, **Spring Security**, **JPA**, y una base de datos relacional. La aplicación gestiona tópicos de discusión para cursos online, con funcionalidades de autenticación, creación y listado de tópicos, filtrado, paginación y manejo de usuarios.

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Insomnia para pruebas

---

## 🔐 Autenticación, seguridad y buenas prácticas

- El proyecto implementa **BCrypt** para encriptar contraseñas y configura `SecurityFilterChain` para deshabilitar CSRF y permitir solicitudes públicas. 
- Las contraseñas de usuario se almacenan en la base de datos utilizando hashing con `BCrypt`.
- Se utiliza `Spring Security` para gestionar la autenticación.
- Todos los endpoints se pueden proteger fácilmente restringiendo su acceso con `.authenticated()` en el `SecurityFilterChain`.

---

## 📦 Estructura del proyecto

```shell
src/
├── controller/         # Controladores REST
├── usuario/            # Modelo, repositorio y DTOs de usuarios
├── topico/             # Modelo, repositorio y DTOs de tópicos
├── infra/              # Configuración de seguridad y utilidades
└── ForoApplication.java
```

---

## 📄 Endpoints principales

### 🔹 Crear usuario (con contraseña hasheada)

```http
POST /usuarios
Content-Type: application/json

{
  "login": "marta.hidalgo",
  "contrasena": "123456"
}
```

---

### 🔹 Crear un tópico

```http
POST /topicos
Content-Type: application/json

{
  "titulo": "Duda con ArrayList",
  "mensaje": "¿Cómo funciona ArrayList?",
  "fecha": "2024-06-01",
  "status": "ABIERTO",
  "autor": "Marta Hidalgo",
  "curso": "Java: Listas y colecciones de datos"
}
```

---

### 🔹 Listar todos los tópicos con paginación y filtros opcionales

```http
GET /topicos?nombreCurso=Java: Listas y colecciones de datos&anio=2024&page=0&size=5&sort=fecha,desc
```

- Soporta paginación con `page`, `size`
- Ordenamiento con `sort`
- Filtro por nombre del curso y año

---

### 🔹 Obtener un tópico por ID

```http
GET /topicos/{id}
```

---

### 🔹 Actualizar un tópico

```http
PUT /topicos/{id}
Content-Type: application/json

{
  "titulo": "Nuevo título",
  "mensaje": "Mensaje actualizado"
}
```

---

### 🔹 Eliminar un tópico

```http
DELETE /topicos/{id}
```

---

---

## 🛠 Configuración

### application.properties (ejemplo)

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foro
spring.datasource.username=root
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Autenticación con JWT

La API implementa seguridad basada en tokens JWT (JSON Web Token). Para acceder a los endpoints protegidos:

1. Regístrate como usuario vía `POST /usuarios`.
2. Inicia sesión con tus credenciales.
3. Recibirás un token JWT, que debes incluir en el encabezado de cada petición protegida:

```
Authorization: Bearer TU_TOKEN
```

## 🧪 Pruebas

Puedes utilizar [Insomnia](https://insomnia.rest/) para probar los endpoints. Asegúrate de tener la aplicación corriendo en:
```
http://localhost:8080
```
**Además: **
- Incluir el token JWT en el encabezado `Authorization`.
- Enviar cuerpos de solicitud válidos en formato JSON.
- Usar los métodos HTTP correctos (`GET`, `POST`, `PUT`, `DELETE`).

---

- ## ✍ Autor

**Domingo Calzadilla**  
Este proyecto forma parte del curso de formación en Spring Boot de [Alura](https://www.aluracursos.com/).

---

## ✅ Próximos pasos

- Documentación con Swagger
