package TDA2024.ExamenParcial.rutacentralizada;

public class ruta {
    public static final String BASE = "alumno";  // Ruta base para el controlador
    public static final String GET_ALL ="/getAll";  // Ruta para obtener todos los alumnos
    public static final String GET_BY_ID = "/getById/{id}";  // Ruta para obtener un alumno por ID
    public static final String CREATE = "/create";  // Ruta para crear un alumno
    public static final String UPDATE = "/update";  // Ruta para actualizar un alumno
    public static final String DELETE =  "/delete/{id}";  // Ruta para eliminar un alumno
}