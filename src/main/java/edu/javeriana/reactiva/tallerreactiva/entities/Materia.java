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
@Table("materias")
public class Materia {
    @Id
    private Long id;
    private String nombre;
    private Integer creditos;
}
