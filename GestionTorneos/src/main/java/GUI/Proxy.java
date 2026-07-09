package GUI;

import Logica.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del intermediario estructural (Proxy/Controlador) del sistema.
 * Su responsabilidad es conectar de manera segura la interfaz gráfica (GUI) con la capa lógica,
 * validando los datos de entrada, gestionando las instanciaciones complejas (como el patrón Strategy)
 * y manteniendo en memoria el estado del torneo que se está creando.
 */
public class Proxy {
    Torneo torneo;
    Participante participante;

    /**
     * Inicializa el proxy asegurando que los punteros del torneo y participante
     * comiencen limpios (en null) antes de iniciar un nuevo proceso de creación.
     */
    public Proxy() {
        this.torneo = null;
        this.participante = null;
    }

    /**
     * Recibe los datos del formulario inicial (Paso 1), los valida de forma segura
     * y delega la creación del torneo. También actúa como una "Fábrica" (Factory)
     * para instanciar la estrategia (Strategy) correcta de acuerdo al formato elegido.
     * @param nombreOrganizador El nombre de la persona a cargo del torneo.
     * @param nombre El nombre oficial del torneo.
     * @param disciplina El deporte o juego electrónico a competir.
     * @param tipoParticipante La modalidad del torneo (ej. EQUIPO o INDIVIDUAL).
     * @param tipoTorneo El formato estructural del torneo (ej. LIGA_SIMPLE, ELIMINACION_DIRECTA).
     */
    public void crearTorneo(String nombreOrganizador, String nombre, String disciplina, TipoParticipante tipoParticipante, TipoTorneoEnum tipoTorneo) {

        // --- BARRERAS DE SEGURIDAD ---
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

        // --- RESOLUCIÓN DEL PATRÓN STRATEGY ---
        // Traduce el Enum visual en una clase lógica real capaz de calcular los enfrentamientos
        CalcularJuegoPlaceholder estrategia = null;
        switch (tipoTorneo) {
            case LIGA_SIMPLE:
                estrategia = new CalcularLigaPlaceholder();
                break;
            case ELIMINACION_DIRECTA:
                estrategia = new CalcularSimplePlaceholder();
                break;
            case ELIMINACION_DOBLE:
                estrategia = new CalcularDoblesPlaceholder();
                break;
        }

        // --- INSTANCIACIÓN FINAL ---
        this.torneo = new Torneo(nombreOrganizador, nombre, disciplina, tipoParticipante, tipoTorneo, estrategia);

        // Visualización temporal para la consola
        System.out.println("[PROXY LOG] ¡Torneo instanciado con éxito en la memoria!");
        System.out.println("  -> Nombre Organizador: " + this.torneo.getNombreOrganizador());
        System.out.println("  -> Nombre: " + this.torneo.getNombre());
        System.out.println("  -> Disciplina: " + this.torneo.getDisciplina());
        System.out.println("  -> Modalidad: " + this.torneo.getTipoParticipante());
        System.out.println("  -> Formato: " + this.torneo.getTipoTorneo().getNombre());
    }

    /**
     * Recibe los datos de contacto desde la interfaz de inscripciones (Paso 2),
     * valida que no existan campos vacíos, crea la entidad Participante y
     * la agrega a la lista oficial del torneo activo.
     * @param nombre Nombre del jugador o del equipo.
     * @param correo Dirección de correo electrónico de contacto.
     * @param numeroTelefonico Número de teléfono de contacto.
     * @param tipoParticipante Modalidad correspondiente al torneo actual.
     */
    public void inscribirParticipante(String nombre, String correo, String numeroTelefonico, TipoParticipante tipoParticipante){

        // --- BARRERAS DE SEGURIDAD ---
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

        // --- INSTANCIACIÓN E INSCRIPCIÓN ---
        this.participante = new Participante(tipoParticipante, nombre, correo, numeroTelefonico);
        this.torneo.inscribirParticipante(participante);

        // Visualización temporal para la consola
        System.out.println("[PROXY LOG] ¡Participante agregado con éxito al torneo!");
        System.out.println("  -> Tipo: " + this.participante.getTipo());
        System.out.println("  -> Nombre: " + this.participante.getNombre());
        System.out.println("  -> Correo: " + this.participante.getCorreo());
        System.out.println("  -> Número de teléfono: " + this.participante.getNumeroTelefonico());

        // Limpiamos la variable temporal por seguridad
        this.participante = null;
    }

    /**
     * Dispara la lógica matemática del torneo para generar los cruces
     * utilizando la estrategia inyectada previamente.
     * @return Una lista de objetos Enfrentamiento ya emparejados, lista para ser dibujada en pantalla.
     */
    public List<Enfrentamiento> generarEnfrentamientos() {
        if (this.torneo == null) return new ArrayList<>();

        return this.torneo.calcularEnfrentamientosTorneo();
    }

    /**
     * Recibe el listado de fechas validadas desde la interfaz (Paso 3) y se las asigna
     * secuencialmente a los partidos que ya se encuentran guardados en la memoria del torneo.
     * @param fechasElegidas Lista de fechas modernas (LocalDateTime) elegidas por el organizador.
     */
    public void guardarFechasEnfrentamientos(List<LocalDateTime> fechasElegidas) {

        // Rescatamos los partidos que ya fueron calculados por el Strategy
        List<Enfrentamiento> partidos = this.torneo.getEnfrentamientos();

        // Se asigna cada fecha a su partido correspondiente
        for (int i = 0; i < partidos.size(); i++) {
            partidos.get(i).setFechaHora(fechasElegidas.get(i));
        }

        System.out.println("[PROXY LOG] Fechas (LocalDateTime) asignadas correctamente.");
    }

    /**
     * Permite a otros componentes del sistema obtener el objeto torneo con todos sus datos.
     * * @return El torneo activo y configurado.
     */
    public Torneo getTorneo() {
        return torneo;
    }

    /**
     * Obtiene el participante que se está procesando actualmente.
     * @return El objeto participante temporal.
     */
    public Participante getParticipante() {
        return participante;
    }
}