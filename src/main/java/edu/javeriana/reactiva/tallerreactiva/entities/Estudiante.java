package edu.javeriana.reactiva.tallerreactiva.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Table("estudiantes")
public class Estudiante {
    @Id
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
}
