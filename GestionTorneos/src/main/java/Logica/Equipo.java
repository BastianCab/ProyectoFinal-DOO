package Logica;

import java.util.ArrayList;

/**
 * Clase que extiende a Participante e implementa la interfaz Inscribible
 * representa a un equipo de personas que pueden ser inscribibles a un torneo,
 * contiene un número identificativo del equipo y las personas que lo componen
 */
public class Equipo extends Participante implements Inscribible {
    private int numEquipo;
    private ArrayList<Participante> equipo;

    /**
     * Crea un equipo que tiene un nombre y un número que lo identifica
     * @param nombreEquipo el nombre del equipo
     * @param numEquipo el número del equipo
     */
    public Equipo(String nombreEquipo, int numEquipo) {
        super(nombreEquipo);
        this.numEquipo = numEquipo;
        equipo = new ArrayList();
    }

    /**
     * Método para añadir un participante al equipo
     * @param participante el participante al que se quiere añadir al equipo
     */
    public void agregarAlEquipo(Participante participante) {
        equipo.add(participante);
    }

    /**
     * Getter del número del equipo
     * @return el número del equipo
     */
    public int getNumEquipo() {
        return numEquipo;
    }

    /**
     * Setter del número del equipo
     * @param numEquipo el número al cual se quiere cambiar
     */
    public void setNumEquipo(int numEquipo) {
        this.numEquipo = numEquipo;
    }

    /**
     * Implementación del método para inscribir al equipo completo
     */
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
