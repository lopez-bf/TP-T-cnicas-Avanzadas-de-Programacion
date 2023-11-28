package edu.palermo.tp.controlvehiculos.patterns;

import edu.palermo.tp.controlvehiculos.dao.entities.ChequeoEntity;
import edu.palermo.tp.controlvehiculos.model.Chequeo;
import edu.palermo.tp.controlvehiculos.model.PuntoChequeo;

public interface ChequeoStrategy {

        int calcularPuntaje(int resultado);

        boolean requiereRechequeo(int puntaje);

        String getObservaciones(int resultado);

        int getPuntoChequeo();
}
