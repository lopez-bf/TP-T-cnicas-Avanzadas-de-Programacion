package edu.palermo.tp.controlvehiculos.dao.repositories;

import edu.palermo.tp.controlvehiculos.dao.entities.ChequeoEntity;
import edu.palermo.tp.controlvehiculos.dao.entities.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChequeoRepository extends JpaRepository<ChequeoEntity,Long> {

    List<ChequeoEntity> findByVehiculo(VehiculoEntity vehiculo);

}
