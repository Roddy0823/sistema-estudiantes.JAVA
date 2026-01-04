package com.sena.estudiantes.dao;

import com.sena.estudiantes.modelo.Estudiante;
import com.sena.estudiantes.util.Constantes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para la entidad Estudiante.
 * Implementa las operaciones CRUD (Create, Read, Update, Delete).
 * 
 * @author SENA
 * @version 1.0
 */
public class EstudianteDAO {

    private final Connection conexion;

    /**
     * Constructor que inicializa la conexión a la base de datos.
     */
    public EstudianteDAO() {
        this.conexion = ConexionBD.obtenerInstancia().obtenerConexion();
    }

    // ==================== OPERACIÓN CREATE (INSERTAR) ====================

    /**
     * Inserta un nuevo estudiante en la base de datos.
     * 
     * @param estudiante Objeto Estudiante a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertar(Estudiante estudiante) {
        try (PreparedStatement pstmt = conexion.prepareStatement(Constantes.SQL_INSERTAR)) {

            // Establecer los parámetros del PreparedStatement
            pstmt.setString(1, estudiante.getNombre());
            pstmt.setString(2, estudiante.getApellido());
            pstmt.setString(3, estudiante.getEmail());
            pstmt.setString(4, estudiante.getCarrera());
            pstmt.setInt(5, estudiante.getSemestre());

            // Ejecutar la inserción
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("✗ Error al insertar estudiante:");
            e.printStackTrace();
            return false;
        }
    }

    // ==================== OPERACIÓN READ (CONSULTAR) ====================

    /**
     * Obtiene todos los estudiantes de la base de datos.
     * 
     * @return Lista de todos los estudiantes
     */
    public List<Estudiante> obtenerTodos() {
        List<Estudiante> estudiantes = new ArrayList<>();

        try (Statement stmt = conexion.createStatement();
                ResultSet rs = stmt.executeQuery(Constantes.SQL_OBTENER_TODOS)) {

            while (rs.next()) {
                Estudiante estudiante = mapearResultSet(rs);
                estudiantes.add(estudiante);
            }

        } catch (SQLException e) {
            System.err.println("✗ Error al obtener estudiantes:");
            e.printStackTrace();
        }

        return estudiantes;
    }

    /**
     * Obtiene un estudiante por su ID.
     * 
     * @param id ID del estudiante a buscar
     * @return Estudiante encontrado o null si no existe
     */
    public Estudiante obtenerPorId(int id) {
        try (PreparedStatement pstmt = conexion.prepareStatement(Constantes.SQL_OBTENER_POR_ID)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSet(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("✗ Error al buscar estudiante por ID:");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Busca estudiantes cuyo nombre o apellido coincida parcialmente.
     * 
     * @param nombre Texto a buscar en nombre o apellido
     * @return Lista de estudiantes que coinciden con la búsqueda
     */
    public List<Estudiante> buscarPorNombre(String nombre) {
        List<Estudiante> estudiantes = new ArrayList<>();

        try (PreparedStatement pstmt = conexion.prepareStatement(Constantes.SQL_BUSCAR_POR_NOMBRE)) {

            String busqueda = "%" + nombre + "%";
            pstmt.setString(1, busqueda);
            pstmt.setString(2, busqueda);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    estudiantes.add(mapearResultSet(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("✗ Error al buscar estudiantes por nombre:");
            e.printStackTrace();
        }

        return estudiantes;
    }

    // ==================== OPERACIÓN UPDATE (ACTUALIZAR) ====================

    /**
     * Actualiza los datos de un estudiante existente.
     * 
     * @param estudiante Estudiante con datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizar(Estudiante estudiante) {
        try (PreparedStatement pstmt = conexion.prepareStatement(Constantes.SQL_ACTUALIZAR)) {

            pstmt.setString(1, estudiante.getNombre());
            pstmt.setString(2, estudiante.getApellido());
            pstmt.setString(3, estudiante.getEmail());
            pstmt.setString(4, estudiante.getCarrera());
            pstmt.setInt(5, estudiante.getSemestre());
            pstmt.setInt(6, estudiante.getId());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("✗ Error al actualizar estudiante:");
            e.printStackTrace();
            return false;
        }
    }

    // ==================== OPERACIÓN DELETE (ELIMINAR) ====================

    /**
     * Elimina un estudiante de la base de datos.
     * 
     * @param id ID del estudiante a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminar(int id) {
        try (PreparedStatement pstmt = conexion.prepareStatement(Constantes.SQL_ELIMINAR)) {

            pstmt.setInt(1, id);

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("✗ Error al eliminar estudiante:");
            e.printStackTrace();
            return false;
        }
    }

    // ==================== MÉTODOS AUXILIARES ====================

    /**
     * Mapea un ResultSet a un objeto Estudiante.
     * Método auxiliar para evitar duplicación de código.
     * 
     * @param rs ResultSet con datos del estudiante
     * @return Objeto Estudiante con los datos mapeados
     * @throws SQLException Si hay error al leer el ResultSet
     */
    private Estudiante mapearResultSet(ResultSet rs) throws SQLException {
        return new Estudiante(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"),
                rs.getString("carrera"),
                rs.getInt("semestre"));
    }

    /**
     * Cuenta el total de estudiantes en la base de datos.
     * 
     * @return Número total de estudiantes
     */
    public int contarEstudiantes() {
        String sql = "SELECT COUNT(*) FROM estudiantes";
        try (Statement stmt = conexion.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("✗ Error al contar estudiantes:");
            e.printStackTrace();
        }
        return 0;
    }
}
