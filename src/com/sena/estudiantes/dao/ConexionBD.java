package com.sena.estudiantes.dao;

import com.sena.estudiantes.util.Constantes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase Singleton para gestionar la conexión a la base de datos.
 * Implementa el patrón Singleton para asegurar una única instancia de conexión.
 * 
 * @author SENA
 * @version 1.0
 */
public class ConexionBD {

    // Instancia única (Patrón Singleton)
    private static ConexionBD instancia;
    private Connection conexion;

    /**
     * Constructor privado.
     * Inicializa la conexión y crea la tabla si no existe.
     */
    private ConexionBD() {
        try {
            // Cargar el driver JDBC de SQLite
            Class.forName(Constantes.DRIVER_JDBC);

            // Establecer la conexion
            conexion = DriverManager.getConnection(Constantes.DB_URL);

            // Crear la tabla si no existe
            crearTablaEstudiantes();

            System.out.println("[OK] Conexion a la base de datos establecida correctamente.");

        } catch (ClassNotFoundException e) {
            System.err.println("[ERROR] Driver JDBC no encontrado.");
            System.err.println("  Asegurese de incluir sqlite-jdbc.jar en el classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al conectar con la base de datos:");
            e.printStackTrace();
        }
    }

    /**
     * Obtiene la instancia única de ConexionBD (Patrón Singleton).
     * 
     * @return Instancia de ConexionBD
     */
    public static synchronized ConexionBD obtenerInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    /**
     * Obtiene la conexión activa a la base de datos.
     * 
     * @return Objeto Connection
     */
    public Connection obtenerConexion() {
        try {
            // Verificar si la conexion esta cerrada y reconectar
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(Constantes.DB_URL);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al verificar/reconectar la conexion:");
            e.printStackTrace();
        }
        return conexion;
    }

    /**
     * Cierra la conexión a la base de datos.
     */
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("[OK] Conexion a la base de datos cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al cerrar la conexion:");
            e.printStackTrace();
        }
    }

    /**
     * Crea la tabla de estudiantes si no existe.
     * Se ejecuta automáticamente al iniciar la conexión.
     */
    private void crearTablaEstudiantes() {
        try (Statement statement = conexion.createStatement()) {
            statement.execute(Constantes.SQL_CREAR_TABLA);
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al crear la tabla de estudiantes:");
            e.printStackTrace();
        }
    }
}
