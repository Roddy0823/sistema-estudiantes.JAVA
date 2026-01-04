# Sistema de Gestión de Estudiantes

## Descripción

Sistema de gestión de estudiantes desarrollado en Java utilizando JDBC para la conexión con base de datos SQLite.
Este proyecto es parte de la evidencia **GA7-220501096-AA2-EV01** del programa ADSO del SENA.

## Características

- ✅ Conexión a base de datos mediante JDBC
- ✅ Operaciones CRUD completas:
  - **C**reate: Registrar nuevos estudiantes
  - **R**ead: Consultar estudiantes (todos, por ID, por nombre)
  - **U**pdate: Actualizar datos de estudiantes
  - **D**elete: Eliminar estudiantes del sistema
- ✅ Interfaz de consola (CLI) intuitiva
- ✅ Validación de datos de entrada
- ✅ Mensajes de confirmación y error

## Estructura del Proyecto

```
SistemaEstudiantes/
├── src/
│   └── com/
│       └── sena/
│           └── estudiantes/
│               ├── app/
│               │   └── Main.java           # Aplicación principal
│               ├── dao/
│               │   ├── ConexionBD.java     # Conexión a base de datos
│               │   └── EstudianteDAO.java  # Operaciones CRUD
│               ├── modelo/
│               │   └── Estudiante.java     # Entidad Estudiante
│               └── util/
│                   └── Constantes.java     # Constantes del sistema
├── lib/
│   └── sqlite-jdbc-3.47.2.0.jar           # Driver JDBC SQLite
├── database/                               # Base de datos SQLite
├── .gitignore
└── README.md
```

## Estándares de Codificación

| Elemento     | Convención                 | Ejemplo                           |
|--------------|----------------------------|-----------------------------------|
| Paquetes     | minúsculas con puntos      | `com.sena.estudiantes.dao`        |
| Clases       | PascalCase (sustantivos)   | `Estudiante`, `EstudianteDAO`     |
| Métodos      | camelCase (verbos)         | `obtenerTodos()`, `insertar()`    |
| Variables    | camelCase (descriptivos)   | `nombreCompleto`, `emailEstudiante` |
| Constantes   | MAYÚSCULAS con guiones     | `DB_URL`, `SQL_INSERTAR`          |

## Requisitos

- Java JDK 17 o superior
- SQLite JDBC Driver (incluido en `/lib`)

## Instrucciones de Ejecución

### Compilar el proyecto

```bash
cd SistemaEstudiantes
javac -cp "lib/*" -d out -sourcepath src src/com/sena/estudiantes/app/Main.java
```

### Ejecutar la aplicación

**Windows:**
```bash
java -cp "out;lib/*" com.sena.estudiantes.app.Main
```

**Linux/Mac:**
```bash
java -cp "out:lib/*" com.sena.estudiantes.app.Main
```

## Uso del Sistema

Al ejecutar la aplicación, se mostrará un menú con las siguientes opciones:

```
=== MENÚ PRINCIPAL ===
1. Registrar nuevo estudiante
2. Listar todos los estudiantes
3. Buscar estudiante por ID
4. Buscar estudiantes por nombre
5. Actualizar datos de estudiante
6. Eliminar estudiante
0. Salir
```

## Tecnologías Utilizadas

- **Lenguaje:** Java 17
- **Base de Datos:** SQLite
- **Conexión DB:** JDBC
- **Control de Versiones:** Git

## Autor

Desarrollado para el SENA - Programa ADSO
Evidencia GA7-220501096-AA2-EV01

## Licencia

Proyecto educativo - SENA 2026
