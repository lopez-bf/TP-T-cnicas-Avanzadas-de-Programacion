package edu.palermo.tp.controlvehiculos.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "vehiculo")
public class VehiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idvehiculo")
    private Long id;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "matricula", nullable = false)
    private String matricula;

    @Column(name = "anio", nullable = false)
    private Integer anio;

    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    private UsuarioEntity usuario;


}
