package Logica;

/**
* Clase que implementa el patrón "Strategy" y la interfaz "Inscribible",
 * representa a un participante genérico el cual puede ser inscrito a un torneo,
 * contiene el nombre del participante
 */
public class Participante {
    TipoParticipante tipo;
    String nombre;
    String correo;
    String telefono;

    /**
     * Constructor principal de la clase Participante.
     * Inicializa un nuevo competidor (ya sea un jugador individual o un equipo)
     * registrando su modalidad.
     *
     * @param tipo   La modalidad del participante obtenida del enumerador (Ej. PERSONA o EQUIPO).
     * @param nombre El nombre completo del jugador o el nombre oficial del equipo.
     */
    public Participante(TipoParticipante tipo, String nombre, String correo, String telefono) throws DatoInvalidoException {
        if (nombre.isBlank() || nombre == null) {
            throw new DatoInvalidoException("El nombre del participante no puede ser nulo o vacío");
        }

        if (correo.isBlank() || correo == null) {
            throw new DatoInvalidoException("El correo no puede estar vacío o nulo");
        }

        if (telefono.isBlank() || telefono == null) {
            throw new DatoInvalidoException("El telefono no puede estar vacío o nulo");
        }

        this.tipo = tipo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    /**
     * Getter del correo del participante
     * @return El correo de contacto del participante
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Getter del número telefónico del participante
      * @return el número telefónico del participante
     */
    public String getTelefono() {
        return telefono;
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
