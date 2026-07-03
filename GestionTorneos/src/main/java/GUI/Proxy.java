package GUI;

import Logica.*;

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
    public void crearTorneo(String nombreOrganizador, String nombre, String disciplina, String tipoTorneo) {
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

        if (tipoTorneo == null || tipoTorneo.trim().isEmpty()) {
            System.out.println("[ERROR PROXY] Debe seleccionar un tipo de torneo.");
            return;
        }

        this.torneo = new Torneo(nombreOrganizador, nombre, disciplina, tipoTorneo);

        // Visualizacion temporal para la consola
        System.out.println("[PROXY LOG] ¡Torneo instanciado con éxito en la memoria!");
        System.out.println("  -> Nombre Organizador: " + this.torneo.getNombreOrganizador());
        System.out.println("  -> Nombre: " + this.torneo.getNombre());
        System.out.println("  -> Disciplina: " + this.torneo.getDisciplina());
        System.out.println("  -> Formato: " + this.torneo.getTipoTorneo());
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
