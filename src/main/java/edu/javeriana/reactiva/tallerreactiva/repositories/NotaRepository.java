package edu.javeriana.reactiva.tallerreactiva.repositories;

import edu.javeriana.reactiva.tallerreactiva.entities.Nota;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface NotaRepository extends ReactiveCrudRepository<Nota, Long> {
    Flux<Nota> findByEstudianteIdAndMateriaId(Long estudianteId, Long materiaId);
    Flux<Nota> findByMateriaId(Long materiaId);
    Flux<Nota> findByEstudianteId(Long estudianteId);
}
