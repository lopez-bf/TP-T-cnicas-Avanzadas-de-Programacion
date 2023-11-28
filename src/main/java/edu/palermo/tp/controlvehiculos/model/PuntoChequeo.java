package edu.palermo.tp.controlvehiculos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PuntoChequeo {
    private Long id;
    private String descripcion;
    private Integer puntaje;
    private String observaciones;

}
