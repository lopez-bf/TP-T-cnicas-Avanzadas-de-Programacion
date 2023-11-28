package edu.palermo.tp.controlvehiculos.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.palermo.tp.controlvehiculos.dao.entities.VehiculoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Turno.",name = "Turnos")
@JsonPropertyOrder({"id"})
public class Turno {
    private Long id;
    private String fechaTurno;
    private String estado;
    private Long vehiculo;
    private String matricula;
}
