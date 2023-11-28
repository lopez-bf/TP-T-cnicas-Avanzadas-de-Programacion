package edu.palermo.tp.controlvehiculos.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.palermo.tp.controlvehiculos.dao.entities.UsuarioEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Vehiculo.",name = "Vehiculos")
@JsonPropertyOrder({"id"})
public class Vehiculo {

    private Long id;
    private String marca;
    private String modelo;
    private String matricula;
    private Integer anioFabricacion;
    private UsuarioEntity usuario;
}
