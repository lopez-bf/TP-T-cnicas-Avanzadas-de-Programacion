package edu.palermo.tp.controlvehiculos.patterns;

import edu.palermo.tp.controlvehiculos.dao.entities.ChequeoEntity;

public class ChequeoEscape implements ChequeoStrategy{

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
            return "Escape no funciona correctamente. ";
        } else {
            return "";
        }
    }

    public int getPuntoChequeo(){
        return 0;
    }

}
