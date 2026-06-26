package Logica;

import java.util.ArrayList;
import java.util.List;

public class Torneo {
    private String nombre;
    private String disciplina;
    private String tipoTorneo;
    private ArrayList<Enfrentamiento> enfrentamientos;
    private ArrayList<Participante> Competidores;
    private List<ObservadorTorneo> observadores = new ArrayList<>();

    public Torneo() {
        this.nombre = "";
        this.disciplina = "";
        this.tipoTorneo = "";
        enfrentamientos = new ArrayList();
        Competidores = new ArrayList();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getTipoTorneo() {
        return tipoTorneo;
    }

    public void setTipoTorneo(String tipoTorneo) {
        this.tipoTorneo = tipoTorneo;
    }

    public ArrayList<Participante> getCompetidores() {
        return Competidores;
    }

    public void setCompetidores(ArrayList<Participante> competidores) {
        Competidores = competidores;
    }

    public ArrayList<Enfrentamiento> getEnfrentamientos() {
        return enfrentamientos;
    }

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
