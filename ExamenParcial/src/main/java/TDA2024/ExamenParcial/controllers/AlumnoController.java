package TDA2024.ExamenParcial.controllers;

import java.util.List;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TDA2024.ExamenParcial.models.AlumnoModel;
import TDA2024.ExamenParcial.services.AlumnoService;
import TDA2024.ExamenParcial.rutacentralizada.ruta;

@RestController
@RequestMapping(ruta.BASE)

public class AlumnoController {
    private static final Logger logger = LoggerFactory.getLogger(AlumnoController.class);
    @Autowired
    AlumnoService alumnoService;

    @GetMapping( ruta.GET_ALL)
    public ResponseEntity<List<AlumnoModel>> getAll() {
        try {
            List<AlumnoModel> alumnos = alumnoService.findAll();
            logger.info("Solicitando lista de todos los alumnos");
            logger.debug("Número de alumnos encontrados: {}", alumnos.size());
            return new ResponseEntity<>(alumnos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error al obtener la lista de alumnos: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(ruta.GET_BY_ID)
    public ResponseEntity<AlumnoModel> getById(@PathVariable int id) {
        try {
            AlumnoModel alumno = alumnoService.findById(id);
            if (alumno == null) {
                logger.warn("Alumno con ID {} no encontrado", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            logger.info("Alumno con ID {} encontrado", id);
            return new ResponseEntity<>(alumno, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error al obtener el alumno con ID {}: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(ruta.CREATE)
    public ResponseEntity<AlumnoModel> create(@RequestBody AlumnoModel model) {
        try {
            if (model == null) {
                logger.warn("Se intentó crear un alumno con datos nulos");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            AlumnoModel nuevoAlumno = alumnoService.add(model);
            logger.info("Alumno creado con éxito: {}", nuevoAlumno.nombre);
            return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error al crear el alumno: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(ruta.UPDATE)
    public ResponseEntity<AlumnoModel> update( @RequestBody AlumnoModel model) {
        try {
            AlumnoModel existingAlumno = alumnoService.findById(model.id);
            if (existingAlumno == null) {
                logger.warn("Alumno con ID {} no encontrado para actualización", model.id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            AlumnoModel updatedAlumno = alumnoService.update(model);
            logger.info("Alumno con ID {} actualizado correctamente", model.id);
            return new ResponseEntity<>(updatedAlumno, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error al actualizar el alumno con ID {}: {}", model.id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(ruta.DELETE)
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            boolean deleted = alumnoService.delete(id);
            if (deleted) {
                logger.info("Alumno con ID {} eliminado correctamente", id);
                return new ResponseEntity<>("Usuario eliminado exitosamente.", HttpStatus.OK);
            } else {
                logger.warn("No se pudo eliminar el alumno con ID {}", id);
                return new ResponseEntity<>("No se pudo eliminar el usuario.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error al eliminar el alumno con ID {}: {}", id, e.getMessage());
            return new ResponseEntity<>("Error al eliminar el alumno.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
