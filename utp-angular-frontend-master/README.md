# 🛒 UTP Technology - E-Commerce Platform

![Angular](https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white)
![TailwindCSS](https://img.shields.io/badge/Tailwind_CSS-38B2AC?style=for-the-badge&logo=tailwind-css&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white)

Bienvenido al repositorio del Frontend de **UTP Technology**, una plataforma web moderna de comercio electrónico diseñada para la venta y gestión de componentes tecnológicos.

🚀 **Enlace de Despliegue en Vivo:** [https://utptechnology-frontend.onrender.com/](https://utptechnology-frontend.onrender.com/)

---

## ✨ Características Principales

*   **🛍️ Tienda y Carrito de Compras**: Catálogo de productos interactivo para clientes, con gestión de carrito y sistema de pago simulado.
*   **🔐 Autenticación y Autorización (JWT)**: Sistema de roles jerárquicos (Administrador, Empleado, Cliente) con protección de rutas dinámicas.
*   **📊 Dashboard Administrativo**: Panel de control exclusivo para la gestión completa de inventario (CRUD de productos reactivo) y administración de personal/usuarios.
*   **🧾 Historial y Facturación (PDF)**: Revisión gráfica de pedidos y generación dinámica de comprobantes de pago descargables (Sincronizado con API Java).
*   **📱 Diseño Responsivo Premium (UI/UX)**: Interfaz construida bajo conceptos de *Glassmorphism* optimizada para escritorio y móviles.

## 🛠️ Tecnologías Utilizadas

*   **Framework Core:** Angular 17+
*   **Estilos y Maquetado:** Tailwind CSS & Variables Nativas SCSS
*   **Componentes UI:** Angular Material
*   **Alertas e Interacciones:** SweetAlert2
*   **Arquitectura de Red:** `HttpClient` Modificado con Interceptores (Auth Bearer Token).

## 🔗 Integración y Arquitectura

Este frontal reacciona en tiempo real a la API diseñada especialmente para él:
*   **Backend:** Spring Boot (Java 17) + Spring Security (JWT)
*   **Base de Datos en la Nube:** Google Firebase (Firestore)

---

## 💻 Instalación Local

Si deseas correr este proyecto en tu entorno de desarrollo, sigue estos rápidos pasos:

1. **Abre tu Terminal y sitúate en la raíz del proyecto:**
   ```bash
   cd utp-angular-frontend-master
   ```

2. **Instala las dependencias de Node.js:**
   ```bash
   npm install
   ```

3. **Ejecuta el servidor de desarrollo:**
   ```bash
   npm start
   # o bien: ng serve
   ```
   *La aplicación estará instantáneamente disponible en `http://localhost:4200/`. El servidor posee Hot-Reload para refrescarse automáticamente si haces cambios en el código.*

## ⚙️ Compilación para Producción

Para generar los archivos estáticos de producción (minificados y optimizados para la web):

```bash
npm run build
```
Este comando generará la carpeta `dist/` en el proyecto, la cual contiene todo el código compilado indispensable para el despliegue en plataformas de Hosting como Render.

---
🛡️ *Desarrollado para el Proyecto Integrador Tecnológico.*
