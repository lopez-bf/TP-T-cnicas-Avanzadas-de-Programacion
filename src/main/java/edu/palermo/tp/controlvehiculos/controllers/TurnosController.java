package edu.palermo.tp.controlvehiculos.controllers;

import edu.palermo.tp.controlvehiculos.dao.entities.TurnoEntity;
import edu.palermo.tp.controlvehiculos.model.Turno;
import edu.palermo.tp.controlvehiculos.services.TurnoService;
import edu.palermo.tp.controlvehiculos.services.TurnoServiceImpl;
import edu.palermo.tp.controlvehiculos.validators.groups.OnCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/turnos")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "API Turnos", description = " Turnos")
public class TurnosController {

    @NonNull
    TurnoServiceImpl turnoService;

    @Operation(summary = "Alta de un turno", description = "alta de turnos")
    @PostMapping("/")
    @CacheEvict("turno")
    public ResponseEntity<?> altaTurno(
            @Parameter(name = "JSON de turno",description = "JSON de turno completo",schema = @Schema(implementation = Turno.class))
            @Validated(OnCreate.class)
            @RequestBody Turno turno){

        Turno turnoCreated = turnoService.createTurnoDisponible(turno);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(turnoCreated.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @Operation(summary = "Solicitud de un turno", description = "alta de turnos")
    @PostMapping("/solicitar")
    @CacheEvict("turno")
    public ResponseEntity<?> solicitarTurno(
            @Parameter(name = "JSON de turno",description = "JSON de turno completo",schema = @Schema(implementation = Turno.class))
            @Validated(OnCreate.class)
            @RequestBody Turno turno){

        Turno turnoCreated = turnoService.createTurno(turno);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(turnoCreated.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        List<TurnoEntity> turnos = turnoService.getAllTurnos();
        return ResponseEntity.ok(turnos);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<?> getTurnosDisponibles(){
        List<TurnoEntity> turnos = turnoService.getTurnosDisponibles();
        return ResponseEntity.ok(turnos);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<?> getTurnosVehiculos(@PathVariable String matricula){
        List<TurnoEntity> turnos = turnoService.getTurnosVehiculo(matricula);
        return ResponseEntity.ok(turnos);
    }


}
