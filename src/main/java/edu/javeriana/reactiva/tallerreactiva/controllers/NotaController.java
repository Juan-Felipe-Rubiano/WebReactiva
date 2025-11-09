package edu.javeriana.reactiva.tallerreactiva.controllers;

import edu.javeriana.reactiva.tallerreactiva.entities.Nota;
import edu.javeriana.reactiva.tallerreactiva.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/notas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotaController {
    private final NotaService notaService;
    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @GetMapping
    public Flux<Nota> obtenerTodasLasNotas() {
        return notaService.obtenerTodasLasNotas();
    }

    /*@GetMapping("/materia/{idMateria}")
    public Flux<Nota> obtenerNotasPorEstudiante(@PathVariable Long idEstudiante) {
        return notaService.obtenerNotasPorEstudiante(idEstudiante);
    }*/
    @PostMapping
    public Mono<Nota> crearNota(@RequestBody Nota nota) {
        return notaService.registrarNotaEstudianteMateria(nota);
    }

    @PutMapping("/{id}")
    public Mono<Nota> actualizarNota(@PathVariable Long id, @RequestBody Nota nota) {
        return notaService.actualizarNota(id, nota);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> eliminarNota(@PathVariable Long id) {
        return notaService.eliminarNota(id);
    }

    @GetMapping(value = "/stream/promedio/{idEstudiante}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Double> promedioReactivo(@PathVariable Long idEstudiante) {
        return notaService.promedioNotasStream(idEstudiante);
    }
}
