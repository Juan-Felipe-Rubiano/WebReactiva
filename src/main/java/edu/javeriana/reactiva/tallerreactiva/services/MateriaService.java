package edu.javeriana.reactiva.tallerreactiva.services;

import edu.javeriana.reactiva.tallerreactiva.entities.Materia;
import edu.javeriana.reactiva.tallerreactiva.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MateriaService {

    @Autowired
    private final MateriaRepository materiaRepository;
    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public Mono<Materia> crearMateria(Materia materia) {
        return materiaRepository.existsByNombre(materia.getNombre())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException("La materia ya existe"));
                    } else {
                        return materiaRepository.save(materia);
                    }
                });
    }

    public Mono<Materia> actualizarMateria(Long id, Materia materia) {
        return materiaRepository.findById(id)
                .flatMap(existingMateria -> {
                    existingMateria.setNombre(materia.getNombre());
                    existingMateria.setCreditos(materia.getCreditos());
                    return materiaRepository.save(existingMateria);
                });
    }

    public  Mono<Void> eliminarMateria(Long id) {
        return materiaRepository.deleteById(id);
    }

    public Flux<Materia> obtenerTodasLasMaterias() {
        return materiaRepository.findAll();
    }
}
