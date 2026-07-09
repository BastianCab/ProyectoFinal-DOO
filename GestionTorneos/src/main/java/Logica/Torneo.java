package Logica;

import java.util.ArrayList;
import java.util.List;

/**
 * clase Torneo que centraliza toda la información del evento (nombre, disciplina, formato),
 * gestiona las listas de competidores y orquesta la generación del calendario.
 * Arquitectónicamente, esta clase aplica el patron de diseño Strategy: Delega la lógica
 * matemática de emparejamiento a la interfaz CalcularJuegoPlaceholder.
 */
public class Torneo {
    private String nombreOrganizador;
    private String nombre;
    private String disciplina;
    private TipoParticipante tipoParticipante;
    private TipoTorneoEnum tipoTorneo;
    private CalcularJuego estrategia;
    private List<Enfrentamiento> enfrentamientos;
    private List<Participante> competidores;
    /**
     * Constructor principal que inicializa el estado base del torneo.
     * Recibe la configuración estructural y la herramienta lógica (Strategy)
     * que utilizará más adelante para armar los partidos.
     * @param nombreOrganizador El nombre de la persona o entidad a cargo.
     * @param nombre El nombre oficial del torneo.
     * @param disciplina El deporte o videojuego que se competirá.
     * @param tipoParticipante La modalidad del torneo (ej. INDIVIDUAL o EQUIPO).
     * @param tipoTorneo El formato de llaves o liga.
     * @param estrategia El algoritmo concreto (Strategy) inyectado para generar los cruces.
     */
    public Torneo(String nombreOrganizador, String nombre, String disciplina,
                  TipoParticipante tipoParticipante, TipoTorneoEnum tipoTorneo,
                  CalcularJuego estrategia) throws DatoInvalidoException {
        if (nombreOrganizador.isBlank() || nombreOrganizador == null) {
            throw new DatoInvalidoException("El nombre del organizador no puede ser nulo o vacío");
        }

        if (nombre.isBlank() || nombre == null){
            throw new DatoInvalidoException("El nombre del torneo no puede ser nulo o vacío");
        }

        if (disciplina == null || disciplina.isBlank()) {
            throw new DatoInvalidoException("La disciplina del torneo no puede ser nula o vacía");
        }

        if (tipoParticipante == null) {
            throw new DatoInvalidoException("El tipo de participante no puede ser nulo");
        }

        if (tipoTorneo == null) {
            throw new DatoInvalidoException("El tipo de torneo no puede ser nulo");
        }

        if (estrategia == null) {
            throw new DatoInvalidoException("La forma de calcular los juegos no puede ser nula");
        }

        this.nombreOrganizador = nombreOrganizador;
        this.nombre = nombre;
        this.disciplina = disciplina;
        this.tipoParticipante = tipoParticipante;
        this.tipoTorneo = tipoTorneo;
        this.estrategia = estrategia;

        this.enfrentamientos = new ArrayList<>();
        this.competidores = new ArrayList<>();
    }

    /**
     * Añade un nuevo competidor a la lista oficial de inscritos del torneo.
     * * @param nuevoParticipante El objeto Participante con sus datos ya validados.
     */
    public void inscribirParticipante(Participante nuevoParticipante) throws DatoInvalidoException {
        if (nuevoParticipante == null) {
            throw new DatoInvalidoException("El nuevo participante no puede ser nulo");
        }

        this.competidores.add(nuevoParticipante);
        System.out.println("Participante " + nuevoParticipante.getNombre() + " inscrito con éxito.");
    }

    /**
     * Ejecuta el patrón Strategy. El torneo le entrega la lista de competidores
     * a su estrategia inyectada, recibe el calendario de partidos armados,
     * lo guarda en su memoria interna y lo devuelve a la interfaz.
     * @return La lista definitiva de enfrentamientos generados.
     */
    public List<Enfrentamiento> calcularEnfrentamientosTorneo() {
        try {
            this.enfrentamientos = this.estrategia.calcularEnfrentamientos(this.competidores);
        } catch (DatoInvalidoException e) {
            System.out.println("Hubo un error al calcular los enfrentamientos");
        }
        return this.enfrentamientos;
    }

    /**
     * Obtiene el número exacto de participantes inscritos actualmente.
     * @return Cantidad de competidores.
     */
    public int getCantidadCompetidores(){
        return competidores.size();
    }

    /**
     * Getter del organizador del torneo.
     * @return El nombre del organizador.
     */
    public String getNombreOrganizador() {
        return nombreOrganizador;
    }

    /**
     * Getter del nombre del torneo.
     * @return El nombre oficial del torneo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter de la disciplina del torneo.
     * @return La disciplina o deporte en competencia.
     */
    public String getDisciplina() {
        return disciplina;
    }

    /**
     * Getter del tipo de participante.
     * @return La modalidad del torneo (ej. EQUIPO).
     */
    public TipoParticipante getTipoParticipante() {
        return tipoParticipante;
    }

    /**
     * Getter del formato del torneo.
     * @return El Enum que representa la estructura del torneo.
     */
    public TipoTorneoEnum getTipoTorneo() {
        return tipoTorneo;
    }


    /**
     * Getter de la lista de competidores
     * @return la lista que contiene a los competidores del torneo
     */
    public List<Participante> getCompetidores() {
        return competidores;
    }

    /**
     * Getter del calendario de partidos.
     * @return La lista que contiene todos los enfrentamientos del torneo.
     */
    public List<Enfrentamiento> getEnfrentamientos() {
        return enfrentamientos;
    }
}