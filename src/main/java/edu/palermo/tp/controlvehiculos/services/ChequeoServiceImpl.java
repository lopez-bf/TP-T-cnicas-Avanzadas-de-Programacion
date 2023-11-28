package edu.palermo.tp.controlvehiculos.services;

import edu.palermo.tp.controlvehiculos.dao.entities.ChequeoEntity;
import edu.palermo.tp.controlvehiculos.dao.entities.PuntoChequeoEntity;
import edu.palermo.tp.controlvehiculos.dao.entities.TurnoEntity;
import edu.palermo.tp.controlvehiculos.dao.entities.VehiculoEntity;
import edu.palermo.tp.controlvehiculos.dao.repositories.ChequeoRepository;
import edu.palermo.tp.controlvehiculos.dao.repositories.PuntoChequeoRepository;
import edu.palermo.tp.controlvehiculos.dao.repositories.VehiculoRepository;
import edu.palermo.tp.controlvehiculos.model.Chequeo;
import edu.palermo.tp.controlvehiculos.model.PuntoChequeo;
import edu.palermo.tp.controlvehiculos.model.Turno;
import edu.palermo.tp.controlvehiculos.patterns.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ChequeoServiceImpl implements ChequeoService {

    @Autowired
    ChequeoRepository chequeoRepository;

    @Autowired
    PuntoChequeoRepository puntoChequeoRepository;

    @Autowired
    VehiculoRepository vehiculoRepository;

    private List<ChequeoStrategy> estrategias = new ArrayList<>();

    public ChequeoServiceImpl() {
        estrategias.add(new ChequeoLuces());
        estrategias.add(new ChequeoFrenos());
        estrategias.add(new ChequeoBateria());
        estrategias.add(new ChequeoNeumaticos());
        estrategias.add(new ChequeoSistemaElectrico());
        estrategias.add(new ChequeoAceiteMotor());
        estrategias.add(new ChequeoEscape());
        estrategias.add(new ChequeoSuspencion());
    }


    public ChequeoEntity realizarChequeo(Chequeo chequeo){

        try{
            Chequeo chequeoResultado = evaluarChequeo(chequeo);
            log.info("chequeoResultado: {}", chequeoResultado.getResultado());

            return createChequeo(chequeoResultado);

        }catch (EntityNotFoundException e){
            throw new RuntimeException("Error al realizar chequeo: " + e.getMessage(), e);
        }


    }

    @Override
    public ChequeoEntity createChequeo(Chequeo chequeo) {
        try {
            ChequeoEntity chequeoEntity = new ChequeoEntity();
            chequeoEntity.setFecha(chequeo.getFecha());
            chequeoEntity.setResultado(chequeo.getResultado());
            chequeoEntity.setEstado(chequeo.getEstado());

            VehiculoEntity vehiculoEntity = vehiculoRepository.findById(chequeo.getVehiculo().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Vehiculo no encontrado con ID: " + chequeo.getVehiculo().getId()));
            chequeoEntity.setVehiculo(vehiculoEntity);
            chequeoEntity.setObservaciones(chequeo.getObservaciones());

            ChequeoEntity savedChequeo = chequeoRepository.save(chequeoEntity);

            List<PuntoChequeoEntity> puntoChequeoEntities = new ArrayList<>();
            for (PuntoChequeo puntoChequeo : chequeo.getPuntochequeo()) {
                PuntoChequeoEntity puntoChequeoEntity = new PuntoChequeoEntity();
                puntoChequeoEntity.setId(puntoChequeo.getId());
                puntoChequeoEntity.setDescripcion(puntoChequeo.getDescripcion());
                puntoChequeoEntity.setPuntaje(puntoChequeo.getPuntaje());
                puntoChequeoEntity.setObservaciones(puntoChequeo.getObservaciones());
                puntoChequeoEntity.setChequeo(savedChequeo);

                PuntoChequeoEntity savedPuntoChequeoEntity = puntoChequeoRepository.save(puntoChequeoEntity);

                puntoChequeoEntities.add(savedPuntoChequeoEntity);
            }


            log.info("chequeoEntity: {}",savedChequeo);
            return savedChequeo;
        } catch (EntityNotFoundException e) {
            log.error("Error al crear Chequeo : {}" + e.getMessage());
            throw new RuntimeException("Error al crear el Chequeo. " + e.getMessage(), e);
        } catch (Exception e) {
            log.error("Error al crear Chequeo: {}", e.getMessage());
            throw new RuntimeException("Error al crear el Chequeo.", e);
        }
    }

    private PuntoChequeoEntity mapPuntoChequeo(PuntoChequeo puntoChequeo) {
        PuntoChequeoEntity puntoChequeoEntity = new PuntoChequeoEntity();
        puntoChequeoEntity.setId(puntoChequeo.getId());
        puntoChequeoEntity.setDescripcion(puntoChequeo.getDescripcion());
        puntoChequeoEntity.setPuntaje(puntoChequeo.getPuntaje());
        puntoChequeoEntity.setObservaciones(puntoChequeo.getObservaciones());
        return puntoChequeoEntity;
    }




   public Chequeo calcularPuntoChequeo(Chequeo chequeo) {

        try{
            List<PuntoChequeo> puntosChequeos = chequeo.getPuntochequeo();

            int puntajeTotal = 0;
            chequeo.setEstado("Seguro");
            String observacion = "";

            for (PuntoChequeo puntoChequeo : puntosChequeos) {
                try {
                    puntajeTotal += estrategias.get(puntosChequeos.indexOf(puntoChequeo)).calcularPuntaje(puntoChequeo.getPuntaje());
                    if (estrategias.get(puntosChequeos.indexOf(puntoChequeo)).requiereRechequeo(puntoChequeo.getPuntaje())) {
                        chequeo.setEstado("Rechequeo");
                        observacion+= estrategias.get(puntosChequeos.indexOf(puntoChequeo)).getObservaciones(puntoChequeo.getPuntaje());
                    }
                } catch (Exception e) {
                    log.error("Error al calcular el puntaje: {}", e.getMessage());
                    throw new RuntimeException("Error al calcular puntaje", e);
                }
            }
            chequeo.setResultado(puntajeTotal);
            chequeo.setObservaciones("Rechequeo".equals(chequeo.getEstado()) ? "El vehiculo requiere rechequeo: "+ observacion : observacion);

            log.info("Estado chequeo: {}", chequeo.getEstado());
            log.info("puntajeTotal: {}", puntajeTotal);

            return chequeo;
        }catch (Exception e){
            log.error("Error al calcular el puntaje.",e.getMessage());
            throw new RuntimeException("Error al calcular el puntaje.",e);
        }
    }

    @Override
    public Chequeo evaluarChequeo(Chequeo chequeo) {

        try {
            Chequeo chequeoResultado = calcularPuntoChequeo(chequeo);

            if ((chequeoResultado.getResultado() >= 80) && (!chequeoResultado.getEstado().equals("Rechequeo"))) {
                chequeoResultado.setEstado("Seguro");
                chequeoResultado.setObservaciones("El vehiculo es seguro.");
            } else if (chequeoResultado.getResultado() < 40) {
                chequeoResultado.setEstado("Rechequeo");
            }

            return chequeoResultado;
        } catch (Exception e) {
            throw new RuntimeException("Error en evaluarChequeo", e);
        }

    }

    @Override
    public List<ChequeoEntity> getChequeosByMatricula(String matricula){
        try {

            VehiculoEntity vehiculo = vehiculoRepository.findByMatricula(matricula);
            List<ChequeoEntity> chequeos = chequeoRepository.findByVehiculo(vehiculo);

            return  chequeos;

        } catch (Exception e) {
            throw new RuntimeException("Error en getChequeosByMatricula", e);
        }

    }

}


