package edu.palermo.tp.controlvehiculos.dao.repositories;

import edu.palermo.tp.controlvehiculos.dao.entities.TurnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<TurnoEntity,Long> {


    List<TurnoEntity> findByEstado(String estado);

    List<TurnoEntity> findByVehiculo(long vehiculo);

}
