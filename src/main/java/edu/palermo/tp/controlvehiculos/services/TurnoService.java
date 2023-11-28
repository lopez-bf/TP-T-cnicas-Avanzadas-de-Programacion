package edu.palermo.tp.controlvehiculos.services;

import edu.palermo.tp.controlvehiculos.dao.entities.TurnoEntity;
import edu.palermo.tp.controlvehiculos.model.Turno;

import java.util.List;

public interface TurnoService {

    public Turno createTurno(Turno turno);

    public List<TurnoEntity> getAllTurnos();

    public List<TurnoEntity> getTurnosDisponibles();

    public List<TurnoEntity> getTurnosVehiculo(String matricula);

    }
