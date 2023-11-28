package edu.palermo.tp.controlvehiculos.patterns;


import edu.palermo.tp.controlvehiculos.dao.entities.ChequeoEntity;
import edu.palermo.tp.controlvehiculos.model.Chequeo;
import edu.palermo.tp.controlvehiculos.model.PuntoChequeo;

public class ChequeoFrenos implements ChequeoStrategy {

    @Override
    public int calcularPuntaje(int resultado) {
        return (resultado > 9 ? resultado : 0);
    }

    @Override
    public boolean requiereRechequeo(int puntaje) {
        return puntaje < 5;
    }

    @Override
    public String getObservaciones(int resultado) {
        if (resultado < 7) {
            return "Los frenos no funcionan correctamente. ";
        } else {
            return "";
        }
    }

    @Override
    public int getPuntoChequeo() {
        return 0;
    }
}
