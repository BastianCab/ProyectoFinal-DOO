package Logica;

/**
 * Define la modalidad de los competidores que participarán en el torneo.
 * Permite al sistema diferenciar si las inscripciones corresponden a
 * jugadores individuales o agrupaciones, lo cual es utilizado para
 * adaptar dinámicamente los textos de la interfaz gráfica y la lógica interna.
 */
public enum TipoParticipante {

    /**
     * Representa a un competidor individual (jugador único).
     */
    PERSONA("Persona"),

    /**
     * Representa a un conjunto de jugadores compitiendo como una sola entidad.
     */
    EQUIPO("Equipo");

    private final String tipoParticipante;
    TipoParticipante(String tipoParticipante){
        this.tipoParticipante = tipoParticipante;
    }
}