# Sistema de Gestion de Estudiantes

## Descripcion

Sistema de gestion de estudiantes desarrollado en Java utilizando JDBC para la conexion con base de datos SQLite.
Este proyecto es parte de la evidencia **GA7-220501096-AA2-EV01** del programa ADSO del SENA.

## Caracteristicas

- Conexion a base de datos mediante JDBC
- Operaciones CRUD completas:
  - **C**reate: Registrar nuevos estudiantes
  - **R**ead: Consultar estudiantes (todos, por ID, por nombre)
  - **U**pdate: Actualizar datos de estudiantes
  - **D**elete: Eliminar estudiantes del sistema
- Interfaz de consola (CLI) intuitiva
- Validacion de datos de entrada
- Mensajes de confirmacion y error

## Requisitos

- **Java JDK 8 o superior** (recomendado JDK 17+)
- SQLite JDBC Driver (incluido en `/lib`)

## Como Ejecutar el Sistema (Windows)

### Opcion 1: Usando los scripts BAT (MAS FACIL)

1. Abrir una ventana de CMD o PowerShell
2. Navegar a la carpeta del proyecto:
   ```
   cd ruta\a\SistemaEstudiantes
   ```
3. Compilar el proyecto:
   ```
   compilar.bat
   ```
4. Ejecutar la aplicacion:
   ```
   ejecutar.bat
   ```

### Opcion 2: Comandos manuales

```bash
cd SistemaEstudiantes
javac -cp "lib/*" -d out -sourcepath src src/com/sena/estudiantes/app/Main.java
java -cp "out;lib/*" com.sena.estudiantes.app.Main
```

### Opcion 3: Usando un IDE (Eclipse, IntelliJ, NetBeans)

1. Abrir el IDE
2. Importar el proyecto como "Proyecto Java Existente"
3. Agregar `lib/sqlite-jdbc-3.47.2.0.jar` al Build Path/Classpath
4. Ejecutar la clase `Main.java`

## Estructura del Proyecto

```
SistemaEstudiantes/
├── src/
│   └── com/sena/estudiantes/
│       ├── app/Main.java           # Aplicacion principal
│       ├── dao/ConexionBD.java     # Conexion JDBC (Patron Singleton)
│       ├── dao/EstudianteDAO.java  # Operaciones CRUD
│       ├── modelo/Estudiante.java  # Entidad POJO
│       └── util/Constantes.java    # Constantes del sistema
├── lib/sqlite-jdbc-3.47.2.0.jar    # Driver JDBC SQLite
├── database/                        # Base de datos SQLite (se crea automaticamente)
├── compilar.bat                     # Script para compilar
├── ejecutar.bat                     # Script para ejecutar
└── README.md
```

## Estandares de Codificacion Aplicados

| Elemento   | Convencion               | Ejemplo                        |
|------------|--------------------------|--------------------------------|
| Paquetes   | minusculas con puntos    | `com.sena.estudiantes.dao`     |
| Clases     | PascalCase (sustantivos) | `Estudiante`, `EstudianteDAO`  |
| Metodos    | camelCase (verbos)       | `obtenerTodos()`, `insertar()` |
| Variables  | camelCase (descriptivos) | `nombreCompleto`, `carrera`    |
| Constantes | MAYUSCULAS con guiones   | `DB_URL`, `SQL_INSERTAR`       |

## Uso del Sistema

Al ejecutar la aplicacion, se mostrara el siguiente menu:

```
================================================================================
   SISTEMA DE GESTION DE ESTUDIANTES - SENA
   Evidencia GA7-220501096-AA2-EV01
================================================================================
[OK] Conexion a la base de datos establecida correctamente.

--------------------------------------------------------------------------------
                         MENU PRINCIPAL
--------------------------------------------------------------------------------
  1. Registrar nuevo estudiante
  2. Listar todos los estudiantes
  3. Buscar estudiante por ID
  4. Buscar estudiantes por nombre
  5. Actualizar datos de estudiante
  6. Eliminar estudiante
  0. Salir
--------------------------------------------------------------------------------
Seleccione una opcion:
```

## Tecnologias Utilizadas

- **Lenguaje:** Java 8+
- **Base de Datos:** SQLite
- **Conexion DB:** JDBC
- **Control de Versiones:** Git + GitHub

## Autor

Roddy Sebastian Holguin Carvajal

## Licencia

Proyecto de evidencia educativa - SENA 2026
