package edu.javeriana.reactiva.tallerreactiva.services;

import edu.javeriana.reactiva.tallerreactiva.entities.Estudiante;
import edu.javeriana.reactiva.tallerreactiva.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public Mono<Estudiante> crearEstudiante(Estudiante estudiante) {
        return estudianteRepository.existsByCorreo(estudiante.getCorreo())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException("El correo ya está en uso"));
                    } else {
                        return estudianteRepository.save(estudiante);
                    }
                });
    }

    public Mono<Estudiante> actualizarEstudiante(Long id, Estudiante estudiante) {
        return estudianteRepository.findById(id)
                .flatMap(existingEstudiante -> {
                    existingEstudiante.setNombre(estudiante.getNombre());
                    existingEstudiante.setCorreo(estudiante.getCorreo());
                    return estudianteRepository.save(existingEstudiante);
                });
    }

    public Mono<Void> eliminarEstudiante(Long id) {
        return estudianteRepository.deleteById(id);
    }

    public Flux<Estudiante> obtenerTodosLosEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Mono<Estudiante> obtenerEstudiantePorId(Long idEstudiante){
        return estudianteRepository.findById(idEstudiante);
    }
}
