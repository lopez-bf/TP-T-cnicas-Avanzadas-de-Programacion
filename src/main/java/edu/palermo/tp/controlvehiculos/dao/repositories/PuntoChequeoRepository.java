package edu.palermo.tp.controlvehiculos.dao.repositories;

import edu.palermo.tp.controlvehiculos.dao.entities.PuntoChequeoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntoChequeoRepository extends JpaRepository<PuntoChequeoEntity,Long> {
}
