# Mantenimiento de ferbo-tools

Este documento describe las **prácticas recomendadas para mantener `ferbo-tools`** actualizado y bien documentado.  
Aplica para cambios de código, versiones, documentación y pruebas unitarias.

---

## 1️⃣ README.md

**Actualizar cuando:**
- Se agregan nuevas funcionalidades o módulos.  
- Cambia la **estructura del proyecto** (carpetas o archivos).  
- Se modifican los **requisitos** (p. ej. soporte de Java, JUnit, Maven/Gradle).  
- Se actualizan ejemplos de uso o snippets de código.  
- Se modifica la sección de **Estado del proyecto**, **Futuras migraciones**, o contribuciones.  

**No actualizar si:**  
- Los cambios son internos y no afectan visibilidad ni uso del proyecto.

---

## 2️⃣ CHANGELOG.md

**Actualizar siempre que:**
- Se agregan nuevas funcionalidades (`Added`).  
- Se corrigen errores (`Fixed`).  
- Se realizan cambios importantes que pueden romper compatibilidad (`Changed` o `Removed`).  
- Se actualizan dependencias críticas o soporte de versiones de Java/JUnit.  

**Buenas prácticas:**
- Usar **versionado semántico** (`MAJOR.MINOR.PATCH`).  
- Incluir la fecha de la versión (`YYYY-MM-DD`).  
- Documentar advertencias de compatibilidad.

---

## 3️⃣ LICENSE

**Actualizar cuando:**
- Se realizan modificaciones significativas en un nuevo año (funcionalidades importantes, migraciones, cambios de arquitectura).  
- Se amplía el rango de años del copyright: `YYYY-YYYY`.

**No actualizar si:**  
- Se hacen cambios menores, refactorings o ajustes internos que no afectan la funcionalidad ni el alcance del software.

---

## 4️⃣ Pruebas unitarias

- Todos los cambios importantes deben tener **tests correspondientes**.  
- Los tests sirven como documentación viva del comportamiento del proyecto.  
- Antes de publicar una nueva versión, **ejecutar todos los tests**.

---

## 5️⃣ Buenas prácticas generales

- Mantener **consistencia entre README, CHANGELOG y LICENSE**.  
- Usar **bloques de código y resaltado** en README para mejorar la comprensión.  
- Documentar cambios importantes en los **Pull Requests** y referenciarlos en CHANGELOG.  
- Seguir las normas de contribución: ramas `develop/main`, nomenclatura `feature/*`, `fix/*`, `refactor/*`, revisión obligatoria.

---

## 6️⃣ Flujo recomendado para nuevas versiones

1. Crear rama `feature/*` o `fix/*` desde `develop`.  
2. Implementar cambios y actualizar tests.  
3. Actualizar README si hay cambios visibles o nuevos requisitos.  
4. Actualizar CHANGELOG con la nueva versión y notas.  
5. Revisar LICENSE si aplica cambio de año o rango de copyright.  
6. Crear Pull Request y obtener aprobación.  
7. Merge a `develop` y etiquetar versión.

---

## 7️⃣ Notas finales

Este documento debe ser consultado por **todos los miembros del equipo que contribuyen a `ferbo-tools`**, asegurando coherencia, claridad y profesionalismo en el mantenimiento del proyecto.