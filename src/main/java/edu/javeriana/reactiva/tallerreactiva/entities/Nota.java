package edu.javeriana.reactiva.tallerreactiva.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table("notas")
public class Nota {
    @Id
    private Long id;
    @Column("materia_id")
    private Long materiaId;
    @Column("estudiante_id")
    private Long estudianteId;
    private String observacion;
    private Double valor;
    private Double porcentaje;
}
