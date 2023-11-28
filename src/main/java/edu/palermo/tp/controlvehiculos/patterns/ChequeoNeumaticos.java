package edu.palermo.tp.controlvehiculos.patterns;

import edu.palermo.tp.controlvehiculos.dao.entities.ChequeoEntity;

public class ChequeoNeumaticos implements ChequeoStrategy{
    @Override
    public int calcularPuntaje(int resultado) {
        return (resultado > 7 ? resultado : 0);
    }

    @Override
    public boolean requiereRechequeo(int puntaje) {
        return puntaje < 5;
    }

    @Override
    public String getObservaciones(int resultado) {
        if (resultado < 5) {
            return "Neumaticos en mal estado. ";
        } else {
            return "";
        }
    }

    public int getPuntoChequeo(){
        return 0;
    }

}
