package Logica;

/**
 * Representa a una persona participante con un nombre que puede ser
 * inscrito a un torneo, extiende la funcionalidad
 * de "Participante" e implementa la interfaz "Inscribible"
 */
public class ParticipantePersona implements Participante {
    private String nombre;
    private String correo;
    /**
     * Crea a una persona que puede ser inscrita al torneo
     * @param nombre el nombre de la persona
     */
    public ParticipantePersona(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    @Override
    public String getNombre() { return this.nombre; }

    @Override
    public String getContacto() { return this.correo; }

    // Un jugador individual no puede contener a otros participantes
    @Override
    public void agregar(Participante p) {
        throw new UnsupportedOperationException("No se pueden agregar participantes a un jugador individual.");
    }

    @Override
    public void eliminar(Participante p) {
        throw new UnsupportedOperationException("No se pueden eliminar participantes de un jugador individual.");
    }

    @Override
    public String toString() {
        return "Nombre del competidor: " ;
    }
}
