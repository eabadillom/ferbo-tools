# Changelog

Todos los cambios importantes en este proyecto serán documentados en este archivo.

Este proyecto sigue versionado semántico (SemVer) y el formato de [Keep a Changelog](https://keepachangelog.com/es-ES/1.0.0/).

---

## [0.1.1] - 07-05-2026

### Added
- Microframework `emresa-tools` listo para uso interno.
- **Módulo domain:** `Entity`, `AggregateRoot`, `ValueObject`, `Identifiable`.
- **Módulo exception:** `BusinessException`, `ValidationException`, `SystemException`, `RuleException`, `ToolException`.
- **Módulo functional:** Interfaces funcionales que soportan excepciones (`ThrowingFunction`, `ThrowingSupplier`, `ThrowingConsumer`, `ThrowingRunnable`).
- **Módulo result:** `OperationResult`, `Message`, `ResultBuilder`.
- **Módulo validation:** Validadores para texto, enteros, objetos y valores monetarios, con sistema de notificación (`Notification`).
- **Módulo util:** Utilidades para fechas (`DateConverter`, `DateFormatter`, `DateArithmetic`), Strings (`StringUtils`) y números (`NumberUtils`).
- **Módulo value.money:** `Money`, `Tax`, `CurrencyUtils`.

###  Testing
- Se agregaron pruebas unitarias que cubren todos los módulos principales.
- Las pruebas sirven como documentación viva del comportamiento esperado.

### Notes / Consideraciones
- Esta versión es **pre-release (0.1.1)** y la API puede cambiar antes de la versión 1.0.0.
- Se recomienda fijar versiones específicas en los proyectos internos que consuman el framework.
- Futuras migraciones planificadas: soporte para Java 11+ y JUnit 5.
- Los cambios previos a esta versión (desde el inicio del proyecto) no están documentados por tratarse de la fase inicial de desarrollo.

---