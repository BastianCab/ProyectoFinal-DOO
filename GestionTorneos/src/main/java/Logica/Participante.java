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
    String numeroTelefonico;

    /**
     * Constructor principal de la clase Participante.
     * Inicializa un nuevo competidor (ya sea un jugador individual o un equipo)
     * registrando su modalidad y su información de contacto básica para el torneo.
     * @param tipo             La modalidad del participante obtenida del enumerador (Ej. PERSONA o EQUIPO).
     * @param nombre           El nombre completo del jugador o el nombre oficial del equipo.
     * @param correo           La dirección de correo electrónico para el envío de notificaciones.
     * @param numeroTelefonico El número de teléfono de contacto del participante o su representante.
     */
    public Participante(TipoParticipante tipo, String nombre, String correo, String numeroTelefonico){
        this.tipo = tipo;
        this.nombre = nombre;
        this.correo = correo;
        this.numeroTelefonico = numeroTelefonico;
    }

    /**
     * Getter del tipo de participante
     * @return devuelve si el participantes es una persona o un equipo
     */
    public TipoParticipante getTipo(){return this.tipo; };

    /**
     * Getter del nombre del participante
     * @return el nombre del participante
     */
    public String getNombre(){ return this.nombre; };

    /**
     * Getter del correo del participante
     * @return el correo del participante
     */
    public String getCorreo(){ return this.correo; };

    /**
     * Getter del numero de telefono del participante
     * @return el numero de telefono del participante
     */
    public String getNumeroTelefonico(){ return this.numeroTelefonico; }
}
