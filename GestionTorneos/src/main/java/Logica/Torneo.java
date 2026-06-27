package Logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un torneo que se quiere organizar, contiene:
 * el nombre del torneo, la disciplina, el formato, una lista de enfrentamientos
 * y una lista de competidores, implementa el patrón "Observer"
 */
public class Torneo {
    private String nombre;
    private String disciplina;
    private String tipoTorneo;
    private ArrayList<Enfrentamiento> enfrentamientos;
    private ArrayList<Participante> Competidores;
    private List<ObservadorTorneo> observadores = new ArrayList<>();

    /**
     * Crea un torneo, inicializa cada característica del torneo,
     * pero se deben definir luego por un organizador
     */
    public Torneo() {
        this.nombre = "";
        this.disciplina = "";
        this.tipoTorneo = "";
        enfrentamientos = new ArrayList();
        Competidores = new ArrayList();
    }

    /**
     * Getter del nombre del torneo
     *
     * @return el nombre del torneo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del nombre del torneo
     *
     * @param nombre el nombre del torneo al cual se desea cambiar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter de la disciplina del torneo
     *
     * @return la disciplina del torneo
     */
    public String getDisciplina() {
        return disciplina;
    }

    /**
     * Setter de la disciplina del torneo
     *
     * @param disciplina la disciplina a la que se desea cambiar
     */
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * Getter del formato del torneo
     *
     * @return el formato del torneo
     */
    public String getTipoTorneo() {
        return tipoTorneo;
    }

    /**
     * Setter del formato del torneo
     *
     * @param tipoTorneo el formato al cual se quiere cambiar
     */
    public void setTipoTorneo(String tipoTorneo) {
        this.tipoTorneo = tipoTorneo;
    }

    /**
     * Getter de la lista de competidores
     *
     * @return la lista que contiene a los competidores del torneo
     */
    public ArrayList<Participante> getCompetidores() {
        return Competidores;
    }

    /**
     * Setter de la lista que contiene a los competidores
     * @param competidores la lista de competidores a la cual se quiere cambiar
     */
    public void setCompetidores(ArrayList<Participante> competidores) {
        Competidores = competidores;
    }

    /**
     * Getter de la lista de enfrentamientos del torneo
     * @return la lista que contiene a los enfrentamientos del torneo
     */
    public ArrayList<Enfrentamiento> getEnfrentamientos() {
        return enfrentamientos;
    }

    /**
     * Setter de la lista de enfrentamientos
     * @param enfrentamientos la lista de enfrentamientos a la que se desea cambiar
     */
    public void setEnfrentamientos(ArrayList<Enfrentamiento> enfrentamientos) {
        this.enfrentamientos = enfrentamientos;
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
}
