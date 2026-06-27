package Logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * Clase que representa al organizador del torneo, contiene su nombre, el torneo
 * que se organiza, el formato del torneo y las fechas de todos los enfrentamientos
 */
public class Organizador {
    public String nombre;
    private Torneo torneo;
    private tipoTorneo formato;
    private ArrayList<LocalDateTime> calendarioEnfrentamientos;

    /**
     * Crea un organizador con un nombre, el torneo y la lista que contiene todas
     * las fechas de los enfrentamientos
     * @param nombre el nombre del organizador del torneo
     */
    public Organizador(String nombre) {
        nombre = this.nombre;
        torneo = new Torneo();
        calendarioEnfrentamientos = new ArrayList();
    }

    /**
     * Define todas las características del torneo:
     * formato, nombre y disciplina
     * @param torneoID el formato del torneo, 1 para eliminatorio simple,
     *                 2 para eliminatorio doble, 3 para de liga y
     *                 4 para todos contra todos
     * @param nombre el nombre del torneo
     * @param Disciplina la disciplina del torneo
     */
    public void definirCaracteristicasTorneo(int torneoID, String nombre, String Disciplina) {
        switch (torneoID) {
            case (1):
                formato = new eliminatorioSimple();
                break;
            case (2):
                formato = new eliminatorioDoble();
                break;
            case (3):
                formato = new deLiga();
                break;
            case (4):
                formato = new todosContraTodos();
                break;
        }

        torneo.setNombre(nombre);
        torneo.setDisciplina(Disciplina);
        torneo.setTipoTorneo(formato.elegirTorneo());
    }

    /**
     * Inscribe a una persona al torneo
     * @param competidor la persona a la cual se quiere inscribir
     */
    public void inscribirCompetidor(ParticipantePersona competidor) {
        torneo.getCompetidores().add(competidor);
    }

    /**
     * Inscribe a un equipo al torneo
     * @param equipo el equipo al que se quiere inscribir
     */
    public void inscribirEquipo(Equipo equipo) {
        torneo.getCompetidores().add(equipo);
    }

    /**
     * Genera automáticamente fechas predeterminadas para cada enfrentamiento
     * (un enfrentamiento cada 2 días, a las 5 PM) y lo añade a la lista
     * que contiene todas las fechas de enfrentamientos
     */
    public void generarCalendarioEnfrentamientos() {
        for (int i = 0; i < torneo.getEnfrentamientos().size(); i++) {
            LocalDateTime fecha = LocalDate.now().plusDays(2 * (i + 1))
                                           .atTime(17,0);
            torneo.getEnfrentamientos().get(i).setFechaHora(fecha);
            calendarioEnfrentamientos.add(fecha);
        }
    }

    /**
     * Genera las fechas de cada enfrentamiento personalizadas
     * @param calen las fechas y horas a la que se desea realizar cada enfrentamiento
     */
    public void seleccionarCalendarioEnfrentamientos(ArrayList<LocalDateTime> calen) {
        calendarioEnfrentamientos = calen;
        for (int i = 0; i < torneo.getEnfrentamientos().size(); i++) {
            torneo.getEnfrentamientos().get(i).setFechaHora(calen.get(i));
        }
    }

    //Deberian ser parte de la GUI
    public void generarBracketInicial() {
    }

    public void actualizarPosicionesBrackets() {
    }

    public void registrarResultadosEnfrentamientos() {
        actualizarPosicionesBrackets();
    }
}
