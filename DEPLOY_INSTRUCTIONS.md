[DEPLOYMENT NOTICE]

¡Hemos avanzado! El despliegue anterior paso a la fase de ejecución, lo cual es excelente. 🎉

El nuevo error (`GdchCredentials`) se debe a un **conflicto de versiones**:
Teníamos una versión muy nueva de `google-cloud-firestore` (3.21.0) chocando con la librería interna de `firebase-admin`.

**Solución Implementada:**
He eliminado la dependencia conflictiva en el `pom.xml` para que `firebase-admin` use su propia versión compatible.

**Pasos a seguir:**
1. Ve a Render.
2. **Manual Deploy** -> **Deploy latest commit** (el commit debe decir: *"fix: Remove conflicting google-cloud-firestore dependency to resolve GdchCredentials error"*).

Esto debería alinear todas las bibliotecas de Google y permitir que la aplicación arranque correctamente. 🛠️
