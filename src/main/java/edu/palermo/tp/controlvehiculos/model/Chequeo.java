package edu.palermo.tp.controlvehiculos.model;

import edu.palermo.tp.controlvehiculos.dao.entities.VehiculoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chequeo {
    private Long id;
    private String fecha;
    private Integer resultado;
    private String estado;
    private VehiculoEntity vehiculo;
    private List<PuntoChequeo> puntochequeo;
    private String observaciones;


}
