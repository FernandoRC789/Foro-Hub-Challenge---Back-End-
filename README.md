# 📌 Foro Hub Challenge - Back-End  
![Java](https://img.shields.io/badge/Java-17-007396?style=for-the-badge&logo=java)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=spring)  
![JWT](https://img.shields.io/badge/JWT-Security-black?style=for-the-badge&logo=jsonwebtokens)  
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql)  

---
## ✅ **Descripción del Proyecto**  

Este proyecto implementa el **back-end** de una aplicación tipo foro, siguiendo las mejores prácticas en **Spring Boot**. Incluye **autenticación con JWT**, **control de acceso**, y un CRUD completo para la gestión de usuarios y tópicos.  

El objetivo principal es construir una API **segura, escalable y mantenible**, aplicando conceptos de **arquitectura limpia**, **inyección de dependencias** y **validación de datos**.

---
## **Funcionalidades**
  ➕ Crear un nuevo tópico: Permite crear nuevos tópicos.
  
  📄 Mostrar todos los tópicos creados: Lista todos los tópicos.

  🔍 Mostrar un tópico específico: Muestra detalles de un tópico por ID.
  
  ✏️ Actualizar un tópico: Actualiza la información de un tópico existente.

  ❌ Eliminar un tópico: Realiza una eliminación lógica del tópico.
---
## 🛠 **Tecnologías utilizadas**  
- **Lenguaje:** Java 17 ☕  
- **Framework:** Spring Boot 3.x  
- **Seguridad:** Spring Security + JWT  
- **Persistencia:** Spring Data JPA + Hibernate  
- **Base de Datos:** MySQL 8  

---

## 🛡 **Buenas Prácticas aplicadas**  
✔ Principio de Responsabilidad Única  
✔ Manejo centralizado de excepciones con `@ControllerAdvice`  
✔ Validaciones con **Bean Validation** (`@Valid`)  
✔ Uso de **DTOs** para separar entidades y modelos expuestos  
✔ Inyección de dependencias mediante **Spring IoC**  

---
## 🚀 **Próximas mejoras**
- ✅ Documentación con Swagger
- ✅ Cache para optimizar rendimiento
- ✅ Tests unitarios con JUnit y Mockito
- ✅ Despliegue con Docker

---

## ⚙ **Configuración y Ejecución**  

### **1️⃣ Requisitos previos**  
- Java 17  
- Maven 3.x  
- MySQL 8  

### **⚙️ Base De Datos **

- Crea una base de datos en MySQL llamada alura_foro_api.
- Configura las siguientes variables de entorno:
  ${DB_NAME_FORO}: alura_foro_api
  ${DB_USER-MYSQL}: Usuario de MySQL
  ${DB_PASSWORD-MYSQL}: Contraseña de MySQL


### **2️⃣ Clonar el repositorio**  
```bash
git clone https://github.com/TU-USUARIO/foro-hub-challenge-backend.git
cd foro-hub-challenge-backend
```
---
## **Author**
