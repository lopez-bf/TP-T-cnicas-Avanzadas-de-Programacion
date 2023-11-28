package edu.palermo.tp.controlvehiculos.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "puntochequeo")
public class PuntoChequeoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idpuntochequeo")
    private Long id;
    @Column(name = "descripcion",nullable = false)
    private String descripcion;
    @Column(name = "puntaje",nullable = false)
    private Integer puntaje;
    @Column(name = "observaciones")
    private String observaciones;
    @ManyToOne
    @JoinColumn(name = "idchequeo",nullable = false)
    private ChequeoEntity chequeo;

}
