package com.sena.estudiantes.modelo;

/**
 * Clase que representa la entidad Estudiante.
 * Sigue el patrón POJO (Plain Old Java Object).
 * 
 * @author SENA
 * @version 1.0
 */
public class Estudiante {
    
    // Atributos privados (Encapsulamiento)
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String carrera;
    private int semestre;
    
    /**
     * Constructor vacío.
     * Requerido para frameworks y JDBC.
     */
    public Estudiante() {
    }
    
    /**
     * Constructor con todos los parámetros excepto ID.
     * Usado para insertar nuevos estudiantes.
     * 
     * @param nombre   Nombre del estudiante
     * @param apellido Apellido del estudiante
     * @param email    Correo electrónico
     * @param carrera  Carrera que cursa
     * @param semestre Semestre actual
     */
    public Estudiante(String nombre, String apellido, String email, String carrera, int semestre) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.carrera = carrera;
        this.semestre = semestre;
    }
    
    /**
     * Constructor completo con ID.
     * Usado para recuperar estudiantes de la base de datos.
     * 
     * @param id       Identificador único
     * @param nombre   Nombre del estudiante
     * @param apellido Apellido del estudiante
     * @param email    Correo electrónico
     * @param carrera  Carrera que cursa
     * @param semestre Semestre actual
     */
    public Estudiante(int id, String nombre, String apellido, String email, String carrera, int semestre) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.carrera = carrera;
        this.semestre = semestre;
    }
    
    // ==================== GETTERS Y SETTERS ====================
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCarrera() {
        return carrera;
    }
    
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    public int getSemestre() {
        return semestre;
    }
    
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    
    /**
     * Retorna una representación en String del objeto.
     * Útil para depuración y visualización en consola.
     * 
     * @return String con los datos del estudiante
     */
    @Override
    public String toString() {
        return String.format(
            "| %-4d | %-15s | %-15s | %-25s | %-20s | %-8d |",
            id, nombre, apellido, email, carrera, semestre
        );
    }
    
    /**
     * Retorna el nombre completo del estudiante.
     * 
     * @return Nombre y apellido concatenados
     */
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
