package Logica;

import java.util.ArrayList;

/**
 * Clase que extiende a Participante e implementa la interfaz Inscribible
 * representa a un equipo de personas que pueden ser inscribibles a un torneo,
 * contiene un número identificativo del equipo y las personas que lo componen
 */
public class ParticipanteEquipo implements Participante {
    private int numEquipo;
    private String nombreEquipo;
    private String correoEquipo;
    private ArrayList<Participante> equipo = new ArrayList();;

    /**
     * Crea un equipo que tiene un nombre y un número que lo identifica
     * @param nombreEquipo el nombre del equipo
     */
    public ParticipanteEquipo( String nombreEquipo, String correoEquipo) {
        this.numEquipo = 0;
        this.nombreEquipo = nombreEquipo;
        this.correoEquipo = correoEquipo;
    }

    @Override
    public String getNombre() { return this.nombreEquipo; }

    @Override
    public String getContacto() { return this.correoEquipo; }

    /**
     * Getter del número del equipo
     * @return el número del equipo
     */
    public int getNumEquipo() {
        return numEquipo;
    }

    /**
     * Método para añadir un participante al equipo
     * @param participante el participante al que se quiere añadir al equipo
     */
    @Override
    public void agregar(Participante participante) {
        equipo.add(participante);
        this.numEquipo += 1;
    }


    @Override
    public void eliminar(Participante p) {
        equipo.remove(p);
        this.numEquipo -= 1;
    }

    @Override
    public String toString() {
        return "El equipo " + numEquipo + "\""  + "\"" +
                "tiene " + equipo.size() + " participantes";
    }
}
