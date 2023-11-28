package edu.palermo.tp.controlvehiculos.mappers;

import edu.palermo.tp.controlvehiculos.dao.entities.TurnoEntity;
import edu.palermo.tp.controlvehiculos.model.Turno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TurnoMapper {

    @Mapping(source = "fecha",target = "fechaTurno")
    List<Turno> mapping(List<TurnoEntity> turnoEntities);
}
