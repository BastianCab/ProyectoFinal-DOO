package Logica;

/**
* Clase que implementa el patrón "Strategy" y la interfaz "Inscribible",
 * representa a un participante genérico el cual puede ser inscrito a un torneo,
 * contiene el nombre del participante
 */
public class Participante {
    TipoParticipante tipo;
    String nombre;

    /**
     * Constructor principal de la clase Participante.
     * Inicializa un nuevo competidor (ya sea un jugador individual o un equipo)
     * registrando su modalidad.
     *
     * @param tipo   La modalidad del participante obtenida del enumerador (Ej. PERSONA o EQUIPO).
     * @param nombre El nombre completo del jugador o el nombre oficial del equipo.
     */
    public Participante(TipoParticipante tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }

    /**
     * Getter del tipo de participante
     * @return devuelve si el participante es una persona o un equipo
     */
    public TipoParticipante getTipo() {
        return this.tipo;
    }

    ;

    /**
     * Getter del nombre del participante
     * @return el nombre del participante
     */
    public String getNombre() {
        return this.nombre;
    }
}
