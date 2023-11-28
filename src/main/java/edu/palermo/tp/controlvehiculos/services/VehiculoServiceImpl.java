package edu.palermo.tp.controlvehiculos.services;


import edu.palermo.tp.controlvehiculos.dao.entities.TurnoEntity;
import edu.palermo.tp.controlvehiculos.dao.entities.VehiculoEntity;
import edu.palermo.tp.controlvehiculos.dao.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoServiceImpl implements VehiculoService{

    @Autowired
    VehiculoRepository vehiculoRepository;


    public void crearVehiculo(VehiculoEntity vehiculo) throws Exception {
            if (vehiculo.getMarca() == null || vehiculo.getMarca().isEmpty()) {
                throw new Exception("El campo 'marca' es obligatorio");
            }
            if (vehiculo.getModelo() == null || vehiculo.getModelo().isEmpty()) {
                throw new Exception("El campo 'modelo' es obligatorio");
            }
            if (vehiculo.getMatricula() == null || vehiculo.getMatricula().isEmpty()) {
                throw new Exception("El campo 'matricula' es obligatorio");
            }
            if (vehiculo.getAnio() == null || vehiculo.getAnio() <= 0) {
                throw new Exception("El campo 'anio' es obligatorio y debe ser mayor que 0");
            }
            vehiculoRepository.save(vehiculo);
        }

    @Override
    public List<VehiculoEntity> getAllVehiculos(){
        List<VehiculoEntity> vehiculoEntities = vehiculoRepository.findAll();

        return vehiculoEntities;
    }




}
