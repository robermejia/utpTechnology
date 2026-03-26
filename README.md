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

## 🧩 Colecciones de la Base de Datos (Firebase Firestore)

La base de datos actual está orquestada en la nube mediante **Firebase Firestore (NoSQL)** y usa las siguientes colecciones:

- `rol`: Roles de usuario (Administrador, Vendedor, Cliente)
- `usuarios`: Autenticación y datos de personal gestionado.
- `clientes`: Información de clientes vinculados a compras.
- `productos`: Catálogo principal de productos con imagen, precio y stock.
- `pedidos`: Compilación o carrito de compras confirmadas.
- `detalles_pedido`: Desglose exacto de cada producto dentro de un pedido.
- `comprobantes`: Instancias físicas y comprobantes generados para ser descargados.

---

## 🚀 Despliegue en Producción

El proyecto completo se encuentra alojado y compilado en los servidores web de Render, ofreciendo una experiencia funcional para los roles administrativos y de clientes.

### 🌐 Vínculo de la Aplicación en Vivo:
👉 **[https://utptechnology-frontend-4n8y.onrender.com](https://utptechnology-frontend-4n8y.onrender.com)**

> **Nota Técnica de Despliegue (Cold Start):** Debido a que la infraestructura del backend (Spring Boot) está alojada en una instancia gratuita (Free Tier) de Render sin persistencia activa en memoria (spin-down temporal por inactividad), los flujos de inicialización del `ApplicationContext` y la reconexión con el pool de conexiones de Firebase Firestore pueden demorar un aproximado de 30 a 60 segundos **únicamente en la primera petición del día o tras 15 minutos de inactividad**. Subsecuentemente, los tiempos de latencia (RTT) en la base de datos volverán a la normalidad operativa en tiempo real.

<center>
  <img src="https://i.ibb.co/nM7BtWcs/screencapture-utptechnology-frontend-onrender-productos-2026-02-27-23-45-19.png" style="width: 100%; border-radius: 8px; box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);">
</center>