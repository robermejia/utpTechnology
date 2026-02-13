[DEPLOYMENT NOTICE]

¡Solucionado! He limpiado el historial y configurado la app para usar el archivo secreto en Render.

**Pasos Finales:**
1. Ve a Render.
2. Asegúrate de que tu "Secret File" en Render se llame exactamente `service-account.json`. (`/etc/secrets/service-account.json`).
3. **Manual Deploy** -> **Deploy latest commit** (el commit debe decir: *"feat: Configure Firebase to load credentials from Render secret file"*).

Ahora tu aplicación buscará las credenciales de forma segura. ¡Debería funcionar! 🎉
