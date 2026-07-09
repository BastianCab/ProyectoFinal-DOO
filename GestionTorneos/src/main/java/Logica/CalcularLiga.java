package Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * por temas de tiempo no se pudo agregar una logica
 * para el formato de liga
 */
public class CalcularLiga implements CalcularJuego{

    public CalcularLiga() {
    }

    @Override
    public List<Enfrentamiento> calcularEnfrentamientos(List<Participante> competidores) {
        List<Enfrentamiento> partidos = new ArrayList<>();

        Collections.shuffle(competidores);

        for (int i = 0; i < competidores.size(); i++) {
            for (int j = i + 1; j < competidores.size(); j++) {
                partidos.add(new Enfrentamiento(competidores.get(i), competidores.get(j)));
            }
        }
        return partidos;
    }

    @Override
    public boolean getEnd() {
        return false;
    }

    @Override
    public int[] getParticipantes() {
        return new int[0];
    }

    @Override
    public void empezar(int i) {

    }

    @Override
    public void siguiente() {

    }
    @Override
    public void enfrentar(int a) {

    }
}
