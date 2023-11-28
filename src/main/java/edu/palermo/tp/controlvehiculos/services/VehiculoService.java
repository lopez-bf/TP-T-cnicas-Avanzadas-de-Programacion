package edu.palermo.tp.controlvehiculos.services;

import edu.palermo.tp.controlvehiculos.dao.entities.VehiculoEntity;
import edu.palermo.tp.controlvehiculos.model.Vehiculo;

import java.util.List;

public interface VehiculoService {

    public void crearVehiculo(VehiculoEntity vehiculo) throws Exception;

    public List<VehiculoEntity> getAllVehiculos();
}
