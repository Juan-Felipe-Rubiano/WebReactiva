package edu.javeriana.reactiva.tallerreactiva.controllers;

import edu.javeriana.reactiva.tallerreactiva.entities.Estudiante;
import edu.javeriana.reactiva.tallerreactiva.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {
    private final EstudianteService estudianteService;
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public Flux<Estudiante> obtenerTodosLosEstudiantes() {
        return estudianteService.obtenerTodosLosEstudiantes();
    }
    @PostMapping
    public Mono<Estudiante> crearEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.crearEstudiante(estudiante);
    }

    @PutMapping("/{id}")
    public Mono<Estudiante> actualizarEstudiante(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        return estudianteService.actualizarEstudiante(id, estudiante);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> eliminarEstudiante(@PathVariable Long id) {
        return estudianteService.eliminarEstudiante(id);
    }
}
