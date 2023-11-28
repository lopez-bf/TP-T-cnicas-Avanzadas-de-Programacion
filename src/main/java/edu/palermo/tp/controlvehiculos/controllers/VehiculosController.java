package edu.palermo.tp.controlvehiculos.controllers;

import edu.palermo.tp.controlvehiculos.dao.entities.TurnoEntity;
import edu.palermo.tp.controlvehiculos.dao.entities.VehiculoEntity;
import edu.palermo.tp.controlvehiculos.model.Turno;
import edu.palermo.tp.controlvehiculos.model.Vehiculo;
import edu.palermo.tp.controlvehiculos.services.VehiculoServiceImpl;
import edu.palermo.tp.controlvehiculos.validators.groups.OnCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/vehiculos")
@RequiredArgsConstructor
public class VehiculosController {

    @NonNull
    VehiculoServiceImpl vehiculoService;

    @Operation(summary = "Alta de un vehiculo", description = "Alta de Vehiculos")
    @PostMapping("/")
    @CacheEvict("vehiculo")
    public ResponseEntity<?> crearVehiculo(
            @Parameter(name = "JSON de vehiculo",description = "JSON de vehiculo completo",schema = @Schema(implementation = Turno.class))
            @Validated(OnCreate.class)
            @RequestBody VehiculoEntity vehiculo) {
        try {
            vehiculoService.crearVehiculo(vehiculo);
            return ResponseEntity.ok(vehiculo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el vehículo: " + e.getMessage());
    }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        List<VehiculoEntity> vehiculos = vehiculoService.getAllVehiculos();
        return ResponseEntity.ok(vehiculos);
    }

}
