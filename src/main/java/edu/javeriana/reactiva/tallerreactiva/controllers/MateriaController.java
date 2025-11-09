package edu.javeriana.reactiva.tallerreactiva.controllers;

import edu.javeriana.reactiva.tallerreactiva.entities.Materia;
import edu.javeriana.reactiva.tallerreactiva.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {
    private final MateriaService materiaService;
    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public Flux<Materia> obtenerTodasLasMaterias() {
        return materiaService.obtenerTodasLasMaterias();
    }

    @PostMapping
    public Mono<Materia> crearMateria(@RequestBody Materia materia) {
        return materiaService.crearMateria(materia);
    }

    @PutMapping("/{id}")
    public Mono<Materia> actualizarMateria(@PathVariable Long id, @RequestBody Materia materia) {
        return materiaService.actualizarMateria(id, materia);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> eliminarMateria(@PathVariable Long id) {
        return materiaService.eliminarMateria(id);
    }
}
