package edu.javeriana.reactiva.tallerreactiva.services;

import edu.javeriana.reactiva.tallerreactiva.entities.Nota;
import edu.javeriana.reactiva.tallerreactiva.repositories.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class NotaService {
    @Autowired
    private final NotaRepository notaRepository;
    @Autowired
    private final EstudianteService estudianteService;
    public NotaService(NotaRepository notaRepository, EstudianteService estudianteService) {
        this.notaRepository = notaRepository;
        this.estudianteService = estudianteService;
    }

    public Flux<Nota> obtenerTodasLasNotas() {
        return notaRepository.findAll();
    }

    public Mono<Nota> registrarNotaEstudianteMateria(Nota nota) {
        return validarPorcentaje(nota)
                .then(notaRepository.save(nota));
    }

    public Mono<Nota> actualizarNota(Long id, Nota nota) {
        return notaRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("La nota con id " + id + " no existe")))
                .flatMap(existingNota -> {
                    existingNota.setValor(nota.getValor());
                    existingNota.setPorcentaje(nota.getPorcentaje());
                    existingNota.setObservacion(nota.getObservacion());
                    return validarPorcentaje(existingNota)
                            .then(notaRepository.save(existingNota));
                });
    }

    public Mono<Void> eliminarNota(Long id) {
        return notaRepository.deleteById(id);
    }

    public Mono<Void> validarPorcentaje(Nota nota){
        return notaRepository.findByEstudianteAndMateriaId(nota.getEstudianteId(), nota.getMateriaId())
                .map(Nota::getPorcentaje)
                .reduce(0.0,Double::sum)
                .flatMap(total -> {
                    if (total + nota.getPorcentaje() > 100.0) {
                        return Mono.error(new IllegalArgumentException("La suma de los porcentajes excede el 100%"));
                    } else {
                        return Mono.empty();
                    }
                });
    }
}
