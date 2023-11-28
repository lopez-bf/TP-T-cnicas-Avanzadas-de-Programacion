package edu.palermo.tp.controlvehiculos.dao.entities;

import edu.palermo.tp.controlvehiculos.model.PuntoChequeo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "chequeo")
public class ChequeoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idchequeo")
    private Long id;
    @Column(name = "fecha",nullable = false)
    private String fecha;
    @Column(name = "resultado",nullable = false)
    private Integer resultado;
    @Column(name = "estado",nullable = false)
    private String estado;
    @Column(name = "observaciones")
    private String observaciones;
    @ManyToOne
    @JoinColumn(name = "idvehiculo",nullable = false)
    private VehiculoEntity vehiculo;
    @OneToMany(mappedBy = "chequeo",cascade = CascadeType.ALL)
    private List<PuntoChequeoEntity> puntoChequeo;

}
