package com.sena.estudiantes.util;

/**
 * Clase de constantes del sistema.
 * Centraliza valores constantes para facilitar mantenimiento.
 * 
 * @author SENA
 * @version 1.0
 */
public final class Constantes {

    // ==================== CONFIGURACION DE BASE DE DATOS ====================

    /** Nombre del archivo de base de datos SQLite */
    public static final String NOMBRE_BD = "estudiantes.db";

    /** URL de conexion JDBC para SQLite */
    public static final String DB_URL = "jdbc:sqlite:database/" + NOMBRE_BD;

    /** Driver JDBC de SQLite */
    public static final String DRIVER_JDBC = "org.sqlite.JDBC";

    // ==================== CONSULTAS SQL ====================

    /** SQL para crear la tabla de estudiantes */
    public static final String SQL_CREAR_TABLA = "CREATE TABLE IF NOT EXISTS estudiantes (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre VARCHAR(50) NOT NULL, " +
            "apellido VARCHAR(50) NOT NULL, " +
            "email VARCHAR(100) UNIQUE NOT NULL, " +
            "carrera VARCHAR(100) NOT NULL, " +
            "semestre INTEGER NOT NULL CHECK(semestre >= 1 AND semestre <= 10)" +
            ")";

    /** SQL para insertar un estudiante */
    public static final String SQL_INSERTAR = "INSERT INTO estudiantes (nombre, apellido, email, carrera, semestre) VALUES (?, ?, ?, ?, ?)";

    /** SQL para obtener todos los estudiantes */
    public static final String SQL_OBTENER_TODOS = "SELECT id, nombre, apellido, email, carrera, semestre FROM estudiantes ORDER BY id";

    /** SQL para obtener un estudiante por ID */
    public static final String SQL_OBTENER_POR_ID = "SELECT id, nombre, apellido, email, carrera, semestre FROM estudiantes WHERE id = ?";

    /** SQL para buscar estudiantes por nombre */
    public static final String SQL_BUSCAR_POR_NOMBRE = "SELECT id, nombre, apellido, email, carrera, semestre FROM estudiantes WHERE nombre LIKE ? OR apellido LIKE ?";

    /** SQL para actualizar un estudiante */
    public static final String SQL_ACTUALIZAR = "UPDATE estudiantes SET nombre = ?, apellido = ?, email = ?, carrera = ?, semestre = ? WHERE id = ?";

    /** SQL para eliminar un estudiante */
    public static final String SQL_ELIMINAR = "DELETE FROM estudiantes WHERE id = ?";

    // ==================== MENSAJES DEL SISTEMA ====================

    public static final String MSG_EXITO_INSERTAR = "[OK] Estudiante registrado exitosamente.";
    public static final String MSG_EXITO_ACTUALIZAR = "[OK] Estudiante actualizado exitosamente.";
    public static final String MSG_EXITO_ELIMINAR = "[OK] Estudiante eliminado exitosamente.";
    public static final String MSG_ERROR_CONEXION = "[ERROR] Error al conectar con la base de datos.";
    public static final String MSG_NO_ENCONTRADO = "[ERROR] Estudiante no encontrado.";
    public static final String MSG_LISTA_VACIA = "No hay estudiantes registrados.";

    // ==================== CONFIGURACION DE INTERFAZ ====================

    /** Metodo auxiliar para repetir un caracter */
    private static String repetir(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static final String SEPARADOR = repetir("=", 80);
    public static final String SEPARADOR_LINEA = repetir("-", 80);

    /** Encabezado de la tabla de estudiantes */
    public static final String ENCABEZADO_TABLA = String.format(
            "| %-3s | %-12s | %-12s | %-20s | %-15s | %-4s |",
            "ID", "NOMBRE", "APELLIDO", "EMAIL", "CARRERA", "SEM");

    // Constructor privado para evitar instanciacion
    private Constantes() {
        throw new IllegalStateException("Clase de utilidad - No se puede instanciar");
    }
}
