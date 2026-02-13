[DEPLOYMENT NOTICE]

¡Backend desplegado! 🚀

Ahora toca el **Frontend (Angular)**. He actualizado el archivo `environment.prod.ts` para que apunte a tu nuevo backend (`https://utptechnology.onrender.com/api/v1`).

**Pasos para desplegar el Frontend:**

1.  En Render, crea un nuevo **Web Service**.
2.  Conecta el mismo repositorio (`utpTechnology`).
3.  **Configuración:**
    *   **Name:** `utp-frontend` (o el que quieras).
    *   **Root Directory:** `utp-angular-frontend-master`
    *   **Runtime:** `Docker`
    *   **Region:** La misma que el backend (recomendado).
4.  Dale a **Create Web Service**.

¡Y listo! Cuando termine, Render te dará una URL para ver tu página web funcionando. 🌐
