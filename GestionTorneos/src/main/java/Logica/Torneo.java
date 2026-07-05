package Logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un torneo que se quiere organizar, contiene:
 * el nombre del torneo, la disciplina, el formato, una lista de enfrentamientos
 * y una lista de competidores, implementa el patrón "Observer"
 */
public class Torneo {
    private String nombreOrganizador;
    private String nombre;
    private String disciplina;
    private TipoParticipante tipoParticipante;
    private TipoTorneoEnum tipoTorneo;
    private ArrayList<Enfrentamiento> enfrentamientos;
    private ArrayList<Participante> competidores;
    private List<ObservadorTorneo> observadores = new ArrayList<>();

    /**
     * Crea un torneo, inicializa cada característica del torneo,
     * pero se deben definir luego por un organizador
     */
    public Torneo(String nombreOrganizador, String nombre, String disciplina, TipoParticipante tipoParticipante, TipoTorneoEnum tipoTorneo) {
        this.nombreOrganizador = nombreOrganizador;
        this.nombre = nombre;
        this.disciplina = disciplina;
        this.tipoParticipante = tipoParticipante;
        this.tipoTorneo = tipoTorneo;
        this.enfrentamientos = new ArrayList();
        this.competidores = new ArrayList();
    }

    /**
     * Agrega de a un solo participante a la lista existente
     */
    public void inscribirParticipante(Participante nuevoParticipante) {
        this.competidores.add(nuevoParticipante);
        System.out.println("Participante " + nuevoParticipante.getNombre() + " inscrito con éxito.");
    }

    /**
     * Agrega de a uno los enfrentamientos decididos por el organizador
     */
    public void agregarEnfrentamiento(Enfrentamiento nuevoEnfrentamiento) {
        this.enfrentamientos.add(nuevoEnfrentamiento);
    }

    public void agregarObservador(ObservadorTorneo observador) {
        observadores.add(observador);
    }

    public void registrarResultado(String partido, String resultado) {
        System.out.println("\n[SISTEMA] Organizador registró: " + partido + " -> " + resultado);

        String datosDelPartido = partido + " quedó " + resultado;

        for (ObservadorTorneo obs : observadores) {
            obs.actualizar(datosDelPartido);
        }
    }
    public int getCantidadCompetidores(){
        return competidores.size();
    }

    /**
     * Getter del nombre del torneo
     * @return el nombre del torneo
     */
    public String getNombreOrganizador() {
        return nombreOrganizador;
    }

    /**
     * Getter del nombre del torneo
     * @return el nombre del torneo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter de la disciplina del torneo
     * @return la disciplina del torneo
     */
    public String getDisciplina() {
        return disciplina;
    }

    /**
     * Getter del tipo de participante del torneo
     * @return el tipo de participante del torneo
     */
    public TipoParticipante getTipoParticipante() {
        return tipoParticipante;
    }

    /**
     * Getter del formato del torneo
     * @return el formato del torneo
     */
    public TipoTorneoEnum getTipoTorneo() {
        return tipoTorneo;
    }


    /**
     * Getter de la lista de competidores
     * @return la lista que contiene a los competidores del torneo
     */
    public ArrayList<Participante> getCompetidores() {
        return competidores;
    }

    /**
     * Getter de la lista de enfrentamientos del torneo
     * @return la lista que contiene a los enfrentamientos del torneo
     */
    public ArrayList<Enfrentamiento> getEnfrentamientos() {
        return enfrentamientos;
    }


}
