# Rápidos y Furiosos S.A.C. — Sistema web con Spring Boot

Aplicación web empresarial para un taller de vehículos: mantenimientos CRUD de clientes, empleados,
vehículos, servicios y sus tablas maestras (marca, color, distrito, rol, sexo, estado civil, tipo de documento),
más una API REST para las mismas entidades.

Proyecto del curso Desarrollo de Soluciones Cloud Básico (ISIL, 2026-2).

## Tecnologías

- Java 25, Spring Boot 3.5 (Spring MVC, Spring Data JPA, Bean Validation)
- Thymeleaf + Bootstrap
- MySQL 8
- Postman para probar la API REST

## Ejecución

1. Ejecutar en MySQL los scripts de `database/`, en orden (crean y llenan la BD `bdrapidosyfuriosos20262`).
2. Revisar usuario y contraseña de MySQL en `src/main/resources/application.properties`.
3. Ejecutar `RapidosyfuriososApplication` desde el IDE, o `mvn spring-boot:run`.
4. Abrir http://localhost:8089/rapidosyfuriosos

La colección de Postman está en `postman/`.

## Estructura

```
src/main/java/com/rapidosyfuriosos/
├── controller/       controladores MVC (vistas Thymeleaf)
├── restcontroller/   API REST
├── service/          lógica de negocio
├── repository/       Spring Data JPA
├── entity/           entidades JPA
└── config/           manejo global de errores
```
