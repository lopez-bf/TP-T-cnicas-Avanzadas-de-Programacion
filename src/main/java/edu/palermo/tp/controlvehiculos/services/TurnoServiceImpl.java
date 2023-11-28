package edu.palermo.tp.controlvehiculos.services;

import edu.palermo.tp.controlvehiculos.dao.entities.TurnoEntity;
import edu.palermo.tp.controlvehiculos.dao.entities.VehiculoEntity;
import edu.palermo.tp.controlvehiculos.dao.repositories.TurnoRepository;
import edu.palermo.tp.controlvehiculos.dao.repositories.VehiculoRepository;
import edu.palermo.tp.controlvehiculos.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements TurnoService{

    @Autowired
    TurnoRepository turnoRepository;

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Override
    public Turno createTurno(Turno turno){

        String fecha = turnoRepository.findById(turno.getId()).get().getFecha();
        VehiculoEntity vehiculo = vehiculoRepository.findByMatricula(turno.getMatricula());

        TurnoEntity turnoEntity = new TurnoEntity(
                turno.getId(),
                fecha,
                turno.getEstado(),
                vehiculo.getId());

        TurnoEntity turnoCreated = turnoRepository.save(turnoEntity);
        return turno;
    }

    public Turno createTurnoDisponible(Turno turno){


        TurnoEntity turnoEntity = new TurnoEntity(
                turno.getId(),
                turno.getFechaTurno(),
                turno.getEstado(),
                turno.getVehiculo());

        TurnoEntity turnoCreated = turnoRepository.save(turnoEntity);
        return turno;
    }



    @Override
    public List<TurnoEntity> getAllTurnos(){
        List<TurnoEntity> turnoEntities = turnoRepository.findAll();

        return turnoEntities;
    }

    @Override
    public List<TurnoEntity> getTurnosDisponibles(){
        List<TurnoEntity> turnosDisponibles = turnoRepository.findByEstado("Disponible");

        return turnosDisponibles;
    }

    @Override
    public List<TurnoEntity> getTurnosVehiculo(String matricula){
        VehiculoEntity vehiculo = vehiculoRepository.findByMatricula(matricula);

        List<TurnoEntity> vehiculos = turnoRepository.findByVehiculo(vehiculo.getId());

        return vehiculos;
    }


}
