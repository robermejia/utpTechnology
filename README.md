# <center>Curso Integrador I: Sistemas Software - Sección 37697
</center>
## Docente: Jhonny Edgard VALVERDE PARDAVE
<center>
<img src="https://i.ibb.co/VjJkQwz/Captura-de-pantalla-2025-01-07-154518.png" style="width: 100% ; aspect-ratio:16/9">
</center>

# UTP Technology: Premium Sales Management System

This is a premium, full-stack application transformed with a state-of-the-art **Glassmorphism UI** and a scalable **Firebase Firestore** backend. 

## 📌 Technologies

- **Frontend:** Angular 20, TypeScript, Tailwind CSS (Custom Design System)
- **Backend:** Spring Boot, Firebase Admin SDK (Cloud Firestore)
- **Database:** Firebase Firestore (NoSQL)
- **UI/UX:** Premium Glassmorphism, Material Icons, 'Outfit' Typography
- **Security:** JWT Authentication (Firebase Auth ready)

## 🧩 Estructura de la base de datos

La base de datos `utp_bd` contiene las siguientes tablas:

- `rol`: Roles de usuario (Administrador, Vendedor, Cliente)
- `usuarios`: Usuarios con clave encriptada
- `clientes`: Información de clientes vinculados a usuarios
- `productos`: Lista de productos con imagen, precio y stock
- `pedidos`: Registra compras realizadas
- `detalles_pedido`: Detalles de cada producto dentro de un pedido
- `comprobantes`: Documento generado por cada pedido (boleta/factura)

> El script `ScriptUtpTechnology.sql` crea e inserta datos de prueba automáticamente.

## 🚀 Cómo ejecutar el proyecto

### 1. Base de datos

- Crear una base de datos MySQL ejecutando el script:


ScriptUtpTechnology.sql


<center>
<img src="https://img001.prntscr.com/file/img001/wiUNYs6QSBGwD0eriFXKxg.png" style="width: 100% ; aspect-ratio:16/9">
</center>


### 2. Despliegue
- http://137.184.30.74:8081/
- http://137.184.30.74:8080/swagger-ui/index.html