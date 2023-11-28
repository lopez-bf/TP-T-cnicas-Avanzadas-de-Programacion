package edu.palermo.tp.controlvehiculos.patterns;

import edu.palermo.tp.controlvehiculos.dao.entities.ChequeoEntity;

public class ChequeoAceiteMotor implements ChequeoStrategy{
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
            return "Aceite en mal estado. ";
        } else {
            return "";
        }
    }

    public int getPuntoChequeo(){
        return 0;
    }

}
