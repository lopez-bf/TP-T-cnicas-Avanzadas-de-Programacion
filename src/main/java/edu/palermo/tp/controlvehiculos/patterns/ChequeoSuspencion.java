package edu.palermo.tp.controlvehiculos.patterns;

import edu.palermo.tp.controlvehiculos.dao.entities.ChequeoEntity;

public class ChequeoSuspencion implements ChequeoStrategy{
    @Override
    public int calcularPuntaje(int resultado) {

        return  resultado;
    }

    @Override
    public boolean requiereRechequeo(int puntaje) {
        return puntaje < 5;
    }

    @Override
    public String getObservaciones(int resultado) {
        if (resultado < 5) {
            return "Suspención no funciona correctamente. ";
        } else {
            return "";
        }
    }

    public int getPuntoChequeo(){
        return 0;
    }

}
