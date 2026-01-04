package com.sena.estudiantes.app;

import com.sena.estudiantes.dao.ConexionBD;
import com.sena.estudiantes.dao.EstudianteDAO;
import com.sena.estudiantes.modelo.Estudiante;
import com.sena.estudiantes.util.Constantes;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal de la aplicación.
 * Proporciona una interfaz de consola (CLI) para gestionar estudiantes.
 * 
 * @author SENA
 * @version 1.0
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static EstudianteDAO estudianteDAO;

    /**
     * Método principal de la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("\n" + Constantes.SEPARADOR);
        System.out.println("   SISTEMA DE GESTIÓN DE ESTUDIANTES - SENA");
        System.out.println("   Evidencia GA7-220501096-AA2-EV01");
        System.out.println(Constantes.SEPARADOR);

        // Inicializar la conexión y el DAO
        estudianteDAO = new EstudianteDAO();

        // Ejecutar el menú principal
        ejecutarMenuPrincipal();

        // Cerrar la conexión al salir
        ConexionBD.obtenerInstancia().cerrarConexion();
        scanner.close();

        System.out.println("\n¡Gracias por usar el Sistema de Gestión de Estudiantes!");
    }

    /**
     * Muestra y ejecuta el menú principal de la aplicación.
     */
    private static void ejecutarMenuPrincipal() {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> registrarEstudiante();
                case 2 -> listarEstudiantes();
                case 3 -> buscarPorId();
                case 4 -> buscarPorNombre();
                case 5 -> actualizarEstudiante();
                case 6 -> eliminarEstudiante();
                case 0 -> System.out.println("\nSaliendo del sistema...");
                default -> System.out.println("\n✗ Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 0);
    }

    /**
     * Muestra el menú de opciones.
     */
    private static void mostrarMenu() {
        System.out.println("\n" + Constantes.SEPARADOR_LINEA);
        System.out.println("                         MENÚ PRINCIPAL");
        System.out.println(Constantes.SEPARADOR_LINEA);
        System.out.println("  1. Registrar nuevo estudiante");
        System.out.println("  2. Listar todos los estudiantes");
        System.out.println("  3. Buscar estudiante por ID");
        System.out.println("  4. Buscar estudiantes por nombre");
        System.out.println("  5. Actualizar datos de estudiante");
        System.out.println("  6. Eliminar estudiante");
        System.out.println("  0. Salir");
        System.out.println(Constantes.SEPARADOR_LINEA);
    }

    // ==================== OPERACIONES CRUD ====================

    /**
     * Registra un nuevo estudiante en el sistema.
     * Implementa la operación CREATE.
     */
    private static void registrarEstudiante() {
        System.out.println("\n--- REGISTRAR NUEVO ESTUDIANTE ---\n");

        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");
        String email = leerTexto("Email: ");
        String carrera = leerTexto("Carrera: ");
        int semestre = leerEnteroEnRango("Semestre (1-10): ", 1, 10);

        Estudiante estudiante = new Estudiante(nombre, apellido, email, carrera, semestre);

        if (estudianteDAO.insertar(estudiante)) {
            System.out.println("\n" + Constantes.MSG_EXITO_INSERTAR);
        } else {
            System.out.println("\n✗ Error al registrar el estudiante.");
        }
    }

    /**
     * Lista todos los estudiantes registrados.
     * Implementa la operación READ (todos).
     */
    private static void listarEstudiantes() {
        System.out.println("\n--- LISTA DE ESTUDIANTES ---\n");

        List<Estudiante> estudiantes = estudianteDAO.obtenerTodos();

        if (estudiantes.isEmpty()) {
            System.out.println(Constantes.MSG_LISTA_VACIA);
        } else {
            mostrarTablaEstudiantes(estudiantes);
            System.out.println("\nTotal de estudiantes: " + estudiantes.size());
        }
    }

    /**
     * Busca un estudiante por su ID.
     * Implementa la operación READ (por ID).
     */
    private static void buscarPorId() {
        System.out.println("\n--- BUSCAR ESTUDIANTE POR ID ---\n");

        int id = leerEntero("Ingrese el ID del estudiante: ");
        Estudiante estudiante = estudianteDAO.obtenerPorId(id);

        if (estudiante != null) {
            System.out.println("\nEstudiante encontrado:");
            mostrarTablaEstudiantes(List.of(estudiante));
        } else {
            System.out.println("\n" + Constantes.MSG_NO_ENCONTRADO);
        }
    }

    /**
     * Busca estudiantes por nombre o apellido.
     * Implementa la operación READ (búsqueda LIKE).
     */
    private static void buscarPorNombre() {
        System.out.println("\n--- BUSCAR ESTUDIANTES POR NOMBRE ---\n");

        String nombre = leerTexto("Ingrese el nombre o apellido a buscar: ");
        List<Estudiante> estudiantes = estudianteDAO.buscarPorNombre(nombre);

        if (estudiantes.isEmpty()) {
            System.out.println("\nNo se encontraron estudiantes con ese criterio.");
        } else {
            System.out.println("\nEstudiantes encontrados:");
            mostrarTablaEstudiantes(estudiantes);
            System.out.println("\nResultados: " + estudiantes.size());
        }
    }

    /**
     * Actualiza los datos de un estudiante existente.
     * Implementa la operación UPDATE.
     */
    private static void actualizarEstudiante() {
        System.out.println("\n--- ACTUALIZAR ESTUDIANTE ---\n");

        int id = leerEntero("Ingrese el ID del estudiante a actualizar: ");
        Estudiante estudiante = estudianteDAO.obtenerPorId(id);

        if (estudiante == null) {
            System.out.println("\n" + Constantes.MSG_NO_ENCONTRADO);
            return;
        }

        System.out.println("\nDatos actuales:");
        mostrarTablaEstudiantes(List.of(estudiante));

        System.out.println("\nIngrese los nuevos datos (deje vacío para mantener el valor actual):\n");

        String nombre = leerTextoOpcional("Nombre [" + estudiante.getNombre() + "]: ");
        if (!nombre.isEmpty())
            estudiante.setNombre(nombre);

        String apellido = leerTextoOpcional("Apellido [" + estudiante.getApellido() + "]: ");
        if (!apellido.isEmpty())
            estudiante.setApellido(apellido);

        String email = leerTextoOpcional("Email [" + estudiante.getEmail() + "]: ");
        if (!email.isEmpty())
            estudiante.setEmail(email);

        String carrera = leerTextoOpcional("Carrera [" + estudiante.getCarrera() + "]: ");
        if (!carrera.isEmpty())
            estudiante.setCarrera(carrera);

        String semestreStr = leerTextoOpcional("Semestre [" + estudiante.getSemestre() + "]: ");
        if (!semestreStr.isEmpty()) {
            try {
                int semestre = Integer.parseInt(semestreStr);
                if (semestre >= 1 && semestre <= 10) {
                    estudiante.setSemestre(semestre);
                }
            } catch (NumberFormatException ignored) {
            }
        }

        if (estudianteDAO.actualizar(estudiante)) {
            System.out.println("\n" + Constantes.MSG_EXITO_ACTUALIZAR);
        } else {
            System.out.println("\n✗ Error al actualizar el estudiante.");
        }
    }

    /**
     * Elimina un estudiante del sistema.
     * Implementa la operación DELETE.
     */
    private static void eliminarEstudiante() {
        System.out.println("\n--- ELIMINAR ESTUDIANTE ---\n");

        int id = leerEntero("Ingrese el ID del estudiante a eliminar: ");
        Estudiante estudiante = estudianteDAO.obtenerPorId(id);

        if (estudiante == null) {
            System.out.println("\n" + Constantes.MSG_NO_ENCONTRADO);
            return;
        }

        System.out.println("\n¿Está seguro de eliminar al estudiante?");
        mostrarTablaEstudiantes(List.of(estudiante));

        String confirmacion = leerTexto("\nEscriba 'SI' para confirmar: ");

        if (confirmacion.equalsIgnoreCase("SI")) {
            if (estudianteDAO.eliminar(id)) {
                System.out.println("\n" + Constantes.MSG_EXITO_ELIMINAR);
            } else {
                System.out.println("\n✗ Error al eliminar el estudiante.");
            }
        } else {
            System.out.println("\nOperación cancelada.");
        }
    }

    // ==================== MÉTODOS AUXILIARES ====================

    /**
     * Muestra una tabla formateada con los estudiantes.
     * 
     * @param estudiantes Lista de estudiantes a mostrar
     */
    private static void mostrarTablaEstudiantes(List<Estudiante> estudiantes) {
        System.out.println(Constantes.SEPARADOR_LINEA);
        System.out.println(Constantes.ENCABEZADO_TABLA);
        System.out.println(Constantes.SEPARADOR_LINEA);

        for (Estudiante e : estudiantes) {
            System.out.println(e);
        }

        System.out.println(Constantes.SEPARADOR_LINEA);
    }

    /**
     * Lee un texto obligatorio del usuario.
     * 
     * @param mensaje Mensaje a mostrar
     * @return Texto ingresado
     */
    private static String leerTexto(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("✗ Este campo es obligatorio.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    /**
     * Lee un texto opcional del usuario.
     * 
     * @param mensaje Mensaje a mostrar
     * @return Texto ingresado (puede estar vacío)
     */
    private static String leerTextoOpcional(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    /**
     * Lee un número entero del usuario.
     * 
     * @param mensaje Mensaje a mostrar
     * @return Número entero ingresado
     */
    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int numero = Integer.parseInt(scanner.nextLine().trim());
                return numero;
            } catch (NumberFormatException e) {
                System.out.println("✗ Debe ingresar un número válido.");
            }
        }
    }

    /**
     * Lee un número entero dentro de un rango específico.
     * 
     * @param mensaje Mensaje a mostrar
     * @param min     Valor mínimo permitido
     * @param max     Valor máximo permitido
     * @return Número entero en el rango
     */
    private static int leerEnteroEnRango(String mensaje, int min, int max) {
        while (true) {
            int numero = leerEntero(mensaje);
            if (numero >= min && numero <= max) {
                return numero;
            }
            System.out.println("✗ El valor debe estar entre " + min + " y " + max + ".");
        }
    }
}
