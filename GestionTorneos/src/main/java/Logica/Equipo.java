package Logica;

import java.util.ArrayList;

public class Equipo extends Participante implements Inscribible {
    private int numEquipo;
    private ArrayList<Participante> equipo;

    public Equipo(String nombreEquipo, int numEquipo) {
        super(nombreEquipo);
        this.numEquipo = numEquipo;
        equipo = new ArrayList();
    }

    public void agregarAlEquipo(Participante participante) {
        equipo.add(participante);
    }

    public int getNumEquipo() {
        return numEquipo;
    }

    public void setNumEquipo(int numEquipo) {
        this.numEquipo = numEquipo;
    }

    @Override
    public void inscribir() {
        for (int i = 0; i < equipo.size(); i++) {
            equipo.get(i).inscribir();
        }
    }

    @Override
    public String toString() {
        return "El equipo " + numEquipo + "\"" + super.getNombre() + "\"" +
                "tiene " + equipo.size() + " participantes";
    }
}
