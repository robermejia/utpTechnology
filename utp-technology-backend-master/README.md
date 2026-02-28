# 🚀 UTP Technology - Backend API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

Bienvenido al repositorio Backend de **UTP Technology**, una robusta API RESTful construida en Java encargada de procesar toda la lógica de negocio, seguridad y persistencia de datos del E-Commerce.

🚀 **Enlace del Frontend en Vivo:** [https://utptechnology-frontend.onrender.com/](https://utptechnology-frontend.onrender.com/)

---

## ✨ Arquitectura y Características

*   **🔒 Seguridad y Autenticación**: Implementación de **Spring Security** con Tokens **JWT** (JSON Web Tokens). Manejo de Sesiones sin estado y filtros de protección de rutas.
*   **👥 Control de Accesos Basado en Roles (RBAC)**: Distinción nativa entre `ADMIN` (Dashboard Total), `EMPLEADO` (Gestión Media) y `CLIENTE` (Compras).
*   **☁️ Base de Datos Serverless**: Conexión nativa con **Google Firebase (Firestore)** a través de Credenciales de SDK para un almacenamiento de documentos (NoSQL) ultra-rápido.
*   **📄 Generador Automático de Facturas**: Servicio de Backend integrado con **iTextPDF** para transformar las compras e historiales instantáneamente en recibos `.pdf` listos para ser descargados y emitidos.
*   **🔌 Estandarización de Interfaz (DTOs)**: Modelos de Transferencia de Datos estrictamente tipados bajo convenciones JSON (`snake_case`) para garantizar un canal libre de errores de sincronía bi-direccional con **Angular**.

## 🛠️ Stack Tecnológico

*   **Lenguaje:** Java 17
*   **Framework Principal:** Spring Boot 3
*   **Módulos Claves:** Spring Web, Spring Security, Spring Boot DevTools
*   **Base de Datos Cloud:** Firebase Admin SDK
*   **Herramientas PDF:** iText Core (7.2.5)

---

## 💻 Instalación y Despliegue Local

Para echar a andar este servidor en tu PC, asegúrate de tener el entorno JDK 17+ instalado, luego sigue estos comandos desde la terminal:

1. **Adéntrate a la Raíz del Proyecto Java:**
   ```bash
   cd utp-technology-backend-master
   ```

2. **Compila e Inicializa Spring Boot con Maven:**
   ```bash
   # En Windows:
   .\mvnw spring-boot:run

   # En Linux/Mac:
   ./mvnw spring-boot:run
   ```
   *La API estará a la escucha y lista para despachar peticiones HTTP en el puerto local por defecto (típicamente `http://localhost:8080`).*

## ⚙️ Creación del Ejecutable JAR

Si estás listo para publicar en un servidor VPS o PaaS, puedes compilar la App entera a un único archivo binario:

```bash
.\mvnw clean package -DskipTests
```
Esto encapsulará las clases resultando en el directorio `target/` listas para su posterior despliegue *Docker*.

---
🔥 *Backend for UTP Technology E-Commerce.*
