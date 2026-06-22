# ferbo-tools

`ferbo-tools` es un microframework ligero en Java 8 diseñado para facilitar tareas comunes de programación. Proporciona utilidades reutilizables enfocadas en buenas prácticas y arquitectura basada en Domain-Driven Design (DDD). 

Ofrece herramientas para validación, manejo de errores, resultados de operación, programación funcional segura y soporte para Value Objects monetarios.
---

## Características principales

- **Domain-Driven Design básico:** `Entity`, `AggregateRoot`, `ValueObject`, `Identifiable` 
- **Manejo de excepciones centralizado:** `BusinessException`, `ValidationException`, `SystemException`, `RuleException`, `ToolException` 
- **Validación extensible:** Validadores para texto, enteros, objetos y valores monetarios con sistema de notificación (`Notification`) 
- **Resultados de operación:** `OperationResult`, `Message`, `ResultBuilder` 
- **Programación funcional segura:** `ThrowingFunction`, `ThrowingSupplier`, `ThrowingConsumer`, `ThrowingRunnable` 
- **Utilidades comunes:** Manejo de Strings, números y fechas 
- **Soporte monetario:** `Money`, `Tax`, `CurrencyUtils`

---

## Estructura del proyecto
```text
README.md
CHANGELOG.md
src
├── main/java/com/ferbo/tools
│ ├── domain
│ │ ├── Entity.java
│ │ ├── AggregateRoot.java
│ │ └── ValueObject.java
│ ├── exception
│ │ └── [varias excepciones]
│ ├── functional
│ │ └── [interfaces Throwing*]
│ ├── result
│ │ └── OperationResult, ResultBuilder
│ ├── util
│ │ └── [utilidades de fechas, strings, números]
│ ├── validation
│ │ └── [validadores y Notification]
│ └── value/money
│   └── Money, Tax, CurrencyUtils
└── test/java/com/ferbo/tools
    └── [tests unitarios correspondientes a cada módulo]
```
---

##  Requisitos y compatibilidad

- Java: 8 (actual)
- JUnit: 4 para pruebas unitarias
- Build tools: Maven o Gradle (opcional)
---

## Futuras migraciones

- Soporte planificado para Java 11+ y JUnit 5
- Cambios mayores serán documentados en el CHANGELOG
- Se notificará cualquier posible ruptura de compatibilidad
---

## Ejemplos de uso

**Validación de texto:**
```Java
TextValidator validator = new TextValidator();
validator.notNull("name", name);
validator.notEmpty("name", name);
```

**Uso de Money:**
```Java
Money price = Money.of(100, "USD");
Money tax = Tax.calculate(price, 0.16);
```

**Resultado de operación:**
```Java
OperationResult result = ResultBuilder.success().message("Operación exitosa").build();
```
---

## Pruebas unitarias

- Los tests cubren todos los módulos principales
- Funcionan como documentación viva del comportamiento esperado
- Se recomienda revisarlas antes de usar o extender el toolkit
---

## Acceso y contribuciones

**Proyecto interno de la empresa:**
- Solo miembros autorizados pueden contribuir 
- Pull Requests obligatorios para cambios 
- Reglas para PR: 
    - Ramas desde develop o main 
    - Nomenclatura: feature/*, fix/*, refactor/* 
    - Incluir pruebas unitarias 
    - Requiere aprobación mínima de un responsable 
---

## Alcance

- **Incluye:** Validación, manejo de resultados, Value Objects comunes, utilidades generales 
- **Excluye:** Lógica de negocio específica, integraciones externas, frameworks web, persistencia
---

## Estado del proyecto

**Versión actual:** 0.1.1
API puede cambiar antes de la versión 1.0.0  
Recomendado fijar versión específica en proyectos internos
---

## Ownership

Mantenido por el equipo interno de sistemas.
Responsable de aprobar cambios, definir lineamientos y gestionar versiones.
---

## 📄 Licencia

`ferbo-tools` es un proyecto **interno** de la empresa y su uso está **limitado a miembros autorizados**.

- No está permitido usar, copiar, distribuir ni modificar fuera del entorno corporativo.
- Todos los derechos reservados por [FERBO].
- Para más detalles, consultar con el equipo de sistemas o el responsable de la librería.
