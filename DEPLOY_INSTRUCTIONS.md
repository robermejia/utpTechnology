[DEPLOYMENT NOTICE]

¡Estamos en la recta final! 🏁

El error `npm ERR! code ERESOLVE` es clásico de Angular: las dependencias son muy estrictas y están chocando entre sí.

**Solución Implementada:**
He modificado el `Dockerfile` del frontend para añadir el flag `--legacy-peer-deps` al comando de instalación. Esto le dice a npm que sea "menos estricto" y permita continuar aunque las versiones no coincidan exactamente.

**Pasos a seguir:**
1. Ve a Render (servicio **utp-frontend**).
2. **Manual Deploy** -> **Deploy latest commit** (el commit debe decir: *"fix: Use --legacy-peer-deps for npm install to resolve dependency conflicts"*).

¡Esto debería desbloquear la instalación y permitir que el frontend se construya correctamente! 🔓
