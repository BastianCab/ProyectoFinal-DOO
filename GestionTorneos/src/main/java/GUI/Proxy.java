package GUI;

import Logica.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del patrón "Proxy", para conectar la GUI con la lógica del gestor
 */
public class Proxy {
    Torneo torneo;
    Participante participante;

    /**
     * Inicializa el proxy y sus atributos
     */
    public Proxy() {
        this.torneo = null;
        this.participante = null;
    }

    /**
     * Método para crear el torneo desde la interfaz gráfica.
     * Recibe los datos del formulario, los valida y luego crea el objeto lógico.
     * * @param nombre El nombre del torneo a crear
     * @param disciplina El deporte o juego del torneo
     * @param tipoTorneo El formato del torneo (ej. Liga, Eliminatoria)
     */
    public void crearTorneo(String nombreOrganizador, String nombre, String disciplina,TipoParticipante tipoParticipante, TipoTorneoEnum tipoTorneo) {
        if (nombreOrganizador == null || nombreOrganizador.trim().isEmpty()) {
            System.out.println("[ERROR PROXY] El nombre del organizador no puede estar vacío.");
            return;
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("[ERROR PROXY] El nombre del torneo no puede estar vacío.");
            return;
        }

        if (disciplina == null || disciplina.trim().isEmpty()) {
            System.out.println("[ERROR PROXY] La disciplina no puede estar vacía.");
            return;
        }

        CalcularJuego estrategia = null;
        switch (tipoTorneo) {
            case LIGA_SIMPLE:
                estrategia = new CalcularLiga(torneo.getCantidadCompetidores());
                break;
            case ELIMINACION_DIRECTA:
                estrategia = new CalcularSimple(torneo.getCantidadCompetidores());
                break;
            case ELIMINACION_DOBLE:
                estrategia = new CalcularDobles(torneo.getCantidadCompetidores());
                break;
        }

        this.torneo = new Torneo(nombreOrganizador, nombre, disciplina, tipoParticipante ,tipoTorneo, estrategia);

        // Visualizacion temporal para la consola
        System.out.println("[PROXY LOG] ¡Torneo instanciado con éxito en la memoria!");
        System.out.println("  -> Nombre Organizador: " + this.torneo.getNombreOrganizador());
        System.out.println("  -> Nombre: " + this.torneo.getNombre());
        System.out.println("  -> Disciplina: " + this.torneo.getDisciplina());
        System.out.println("  -> Modalidad: " + this.torneo.getTipoParticipante());
        System.out.println("  -> Formato: " + this.torneo.getTipoTorneo().getNombre());
    }

    public void inscribirParticipante(String nombre, String correo, String numeroTelefonico, TipoParticipante tipoParticipante){
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("[ERROR PROXY] El nombre no puede estar vacío.");
            return;
        }

        if (correo == null || correo.trim().isEmpty()) {
            System.out.println("[ERROR PROXY] El correo no puede estar vacío.");
            return;
        }

        if (numeroTelefonico == null || numeroTelefonico.trim().isEmpty()) {
            System.out.println("[ERROR PROXY] El número de teléfono no puede estar vacío.");
            return;
        }
        this.participante = new Participante(tipoParticipante, nombre, correo, numeroTelefonico);
        this.torneo.inscribirParticipante(participante);

        // Visualizacion temporal para la consola
        System.out.println("[PROXY LOG] ¡Participante agregado con éxito al torneo!");
        System.out.println("  -> Tipo: " + this.participante.getTipo());
        System.out.println("  -> Nombre: " + this.participante.getNombre());
        System.out.println("  -> Correo: " + this.participante.getCorreo());
        System.out.println("  -> Número de teléfono: " + this.participante.getNumeroTelefonico());

        this.participante = null;
    }

    public List<Enfrentamiento> generarEnfrentamientos() {
        if (this.torneo == null) return new ArrayList<>();

        return this.torneo.calcularEnfrentamientosTorneo();
    }

    public void guardarFechasEnfrentamientos(List<LocalDateTime> fechasElegidas) {
        List<Enfrentamiento> partidos = this.torneo.getEnfrentamientos();

        for (int i = 0; i < partidos.size(); i++) {
            partidos.get(i).setFechaHora(fechasElegidas.get(i));
        }

        System.out.println("[PROXY LOG] Fechas (LocalDateTime) asignadas correctamente.");
    }

    /**
     * Getter del torneo
     * @return el torneo creado
     */
    public Torneo getTorneo() {
        return torneo;
    }


    /**
     * Getter del participante
     * @return el participante
     */
    public Participante getParticipante() {
        return participante;
    }
}