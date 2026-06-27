package Logica;

/**
 * Representa a una persona participante con un nombre que puede ser
 * inscrito a un torneo, extiende la funcionalidad
 * de "Participante" e implementa la interfaz "Inscribible"
 */
public class ParticipantePersona extends Participante implements Inscribible {
    /**
     * Crea a una persona que puede ser inscrita al torneo
     * @param nombre el nombre de la persona
     */
    public ParticipantePersona(String nombre) {
        super(nombre);
    }

    /**
     * Representa que la persona puede ser inscrita
     */
    @Override
    public void inscribir() {
    }

    @Override
    public String toString() {
        return "Nombre del competidor: " + super.getNombre();
    }
}
