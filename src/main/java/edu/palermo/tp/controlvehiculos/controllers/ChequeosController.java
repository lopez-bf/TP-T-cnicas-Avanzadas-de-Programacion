package edu.palermo.tp.controlvehiculos.controllers;

import edu.palermo.tp.controlvehiculos.dao.entities.ChequeoEntity;
import edu.palermo.tp.controlvehiculos.dao.entities.TurnoEntity;
import edu.palermo.tp.controlvehiculos.dao.entities.VehiculoEntity;
import edu.palermo.tp.controlvehiculos.dao.repositories.ChequeoRepository;
import edu.palermo.tp.controlvehiculos.model.Chequeo;
import edu.palermo.tp.controlvehiculos.model.Turno;
import edu.palermo.tp.controlvehiculos.services.ChequeoServiceImpl;
import edu.palermo.tp.controlvehiculos.validators.groups.OnCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chequeos")
public class ChequeosController {

    @Autowired
    ChequeoServiceImpl chequeoService;

    @Autowired
    ChequeoRepository chequeoRepository;



    @Operation(summary = "Chequeo de un vehiculo", description = "Chequeo de Vehiculos")
    @PostMapping("/")
    @CacheEvict("chequeo")
    public ResponseEntity<?> realizarChequeo(
            @Parameter(name = "JSON de Chequeo",description = "JSON de chequeo completo",schema = @Schema(implementation = Chequeo.class))
            @Validated(OnCreate.class)
            @RequestBody Chequeo chequeo) {
        try {
            if (chequeo.getVehiculo() == null ) {
                throw new Exception("El Vehiculo es obligatorio");
            }
            if (chequeo.getPuntochequeo() == null || chequeo.getPuntochequeo().isEmpty()) {
                throw new Exception("Punto chequeo es obligatorio");
            }

            ChequeoEntity chequeo1 = chequeoService.realizarChequeo(chequeo);
            return ResponseEntity.ok(chequeo1);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al realizar chequeo: " + e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        List<ChequeoEntity> chequeos = chequeoRepository.findAll();
        return ResponseEntity.ok(chequeos);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<?> getChequeosByMatricula(@PathVariable String matricula){
        List<ChequeoEntity> chequeos = chequeoService.getChequeosByMatricula(matricula);
        return ResponseEntity.ok(chequeos);
    }


}
