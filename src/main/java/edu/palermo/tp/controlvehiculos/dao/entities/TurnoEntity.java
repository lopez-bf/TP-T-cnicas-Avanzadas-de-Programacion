package edu.palermo.tp.controlvehiculos.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "turno")
public class TurnoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idturno")
    private Long id;
    @Column(name = "fecha",nullable = false)
    private String fecha;
    @Column(name = "estado",nullable = false)
    private String estado;
   // @ManyToOne
  //  @JoinColumn(name = "idvehiculo",nullable = false)
    @Column(name = "idvehiculo", nullable = true)
    private long vehiculo;


}
