package edu.palermo.tp.controlvehiculos.dao.repositories;

import edu.palermo.tp.controlvehiculos.dao.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {

}
