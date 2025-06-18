# 🔐 API Demo JWT - Spring Boot

Esta API RESTful fue desarrollada con **Spring Boot** y tiene como objetivo principal **gestionar autenticación mediante JSON Web Tokens (JWT)**. Permite realizar operaciones CRUD sobre empleados de manera segura mediante endpoints protegidos con tokens JWT.

---

## 🚀 Comenzando

Estas instrucciones te ayudarán a ejecutar el proyecto en un entorno local para fines de desarrollo y pruebas.

---

## 📋 Requisitos Previos

Asegúrate de tener instalado lo siguiente:

- 🐘 PostgreSQL (versión recomendada: 13+)
- ☕ JDK 17
- 🧠 IDE como IntelliJ IDEA, Eclipse o VS Code
- 🛠️ Maven 3.8+ (opcional si tu IDE lo incluye)

---

## 🔧 Instalación

1. **Clona el repositorio**

```bash
git https://github.com/mhuahuacondori/demo-jwt.git
cd demo-jwt
```

2. **Configura la base de datos**

```bash
- Crear una base de datos con el nombre: demojwtdb
- Ejecuta el script `schema.sql` ubicado en `src/main/resources/` para crear las tablas necesarias.
```

3. **Configura el archivo `application.yaml`**

Ubicado en `src/main/resources/`, modifica las siguientes propiedades según tu entorno:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/demojwtdb
    username: tu_usuario
    password: tu_contraseña
```

---

## ▶️ Ejecutando el Proyecto

Puedes ejecutar el proyecto de cualquiera de las siguientes formas:

- Desde tu IDE ejecutando la clase principal `DemoJwtApplication.java`.
- Desde la terminal:

```bash
./mvnw spring-boot:run
```

---

## 🧪 Endpoints y Pruebas

Puedes probar los endpoints usando herramientas como **Postman** o **Insomnia**.

### 🔑 Autenticación (Login)

**POST** `/login`

- **Headers:**

```http
Content-Type: application/json
```

- **Body:**

```json
{
  "email": "usuario@correo.com",
  "password": "clave"
}
```

Respuesta: Se generará un JWT válido para las próximas peticiones.

---

### 📚 Endpoints Protegidos (CRUD Empleados)

> ⚠️ Todos los endpoints siguientes requieren el header `Authorization: Bearer <token>` y `transactionId: <id>`

#### 📥 GET - Listar empleados paginados

`GET /templeados?page={page}&size={size}&sort={sort}`
> Retorna empleados con paginación.

#### 📥 GET - Obtener empleado por ID

`GET /empleados/{id}`
> Retorna un empleado específico.

#### ➕ POST - Crear empleado

`POST /empleados`
> Crea un nuevo registro de empleado.

#### ✏️ PUT - Actualizar empleado

`PUT /empleados/{id}`
> Actualiza los datos de un empleado existente.

#### ❌ DELETE - Eliminar empleado

`DELETE /empleados/{id}`
> Elimina un empleado por su ID.

---

## 🛠️ Tecnologías y Librerías

### Frameworks y Lenguaje

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Java 17](https://openjdk.org/projects/jdk/17/)
- [PostgreSQL](https://www.postgresql.org/)

### Dependencias Principales

```text
Spring Boot Starters:
- spring-boot-starter-web         // API REST
- spring-boot-starter-data-jpa    // Persistencia JPA
- spring-boot-starter-security    // Seguridad
- spring-boot-starter-validation  // Validación de datos
- spring-boot-starter-log4j2      // Logging con Log4j2

Autenticación JWT:
- jjwt-api
- jjwt-impl
- jjwt-jackson

Base de Datos:
- postgresql: driver para conexión JDBC

Desarrollo:
- spring-boot-devtools
- lombok
```

---

## 📂 Estructura de Paquetes Recomendada

```
com.hibernate.demojwt
├── common            # Clases auxiliares y constantes compartidas
├── config            # Configuraciones generales (seguridad, CORS, etc.)
├── controller        # Controladores REST para manejar solicitudes HTTP
├── dto               # Objetos de transferencia de datos (Data Transfer Objects)
├── entity            # Entidades JPA que representan tablas en la base de datos
├── exception         # Manejo global de errores y excepciones personalizadas
├── mapper            # Conversión entre DTOs y entidades
├── repository        # Interfaces JPA para acceso a la base de datos
├── security          # Configuración de seguridad y autenticación JWT
├── service           # Definición de interfaces para lógica de negocio
├── service.impl      # Implementaciones de servicios con reglas de negocio
├── util              # Clases de utilidad y funciones auxiliares
└── DemoJwtApplication.java  # Punto de entrada de la aplicación Spring Boot
```

---

## 💡 Conceptos Clave

- **JWT (JSON Web Token):** Mecanismo seguro y ligero para la transmisión de información entre partes como un objeto JSON.
- **Spring Security:** Framework de autenticación y autorización.
- **JPA (Hibernate):** Abstracción ORM para operaciones CRUD con base de datos.

---

## 🎁 Agradecimientos

- Gracias por visitar este repositorio.
- Este proyecto es un ejemplo base, ideal para integrarse en sistemas con control de acceso mediante JWT.

---

## Expresiones de Gratitud 🎁

* Gracias  🤓.

---
⌨️ con ❤️ por [mhuahuacondori](https://github.com/mhuahuacondori) 😊