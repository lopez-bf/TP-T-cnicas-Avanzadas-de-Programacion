package edu.palermo.tp.controlvehiculos.dao.repositories;

import edu.palermo.tp.controlvehiculos.dao.entities.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity,Long> {

    VehiculoEntity findByMatricula(String matricula);

}
