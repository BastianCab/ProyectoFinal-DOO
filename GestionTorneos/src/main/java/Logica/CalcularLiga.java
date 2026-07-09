package Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalcularLiga implements CalcularJuego{

    public CalcularLiga() {
    }

    @Override
    public int getTipo(){
        return 3;
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
    public int getCompetidores() {
        return 666;
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
