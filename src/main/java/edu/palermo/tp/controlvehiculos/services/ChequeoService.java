package edu.palermo.tp.controlvehiculos.services;

import edu.palermo.tp.controlvehiculos.dao.entities.ChequeoEntity;
import edu.palermo.tp.controlvehiculos.model.Chequeo;

import java.util.List;

public interface ChequeoService {

    public Chequeo calcularPuntoChequeo(Chequeo chequeo);

    public ChequeoEntity createChequeo(Chequeo chequeo);

    public Chequeo evaluarChequeo(Chequeo chequeo);

    public List<ChequeoEntity> getChequeosByMatricula(String matricula);




}
