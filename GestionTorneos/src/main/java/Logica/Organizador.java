package Logica;

import java.util.Date;

public class Organizador {
    public String nombre;
    private Torneo torneo;
    private tipoTorneo formato;
    public Organizador(String nombre, int torneoID) {
        nombre = this.nombre;
        torneo = new Torneo();

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
    }

    public void definirCaracteristicasTorneo(String nombre, String Disciplina) {
        torneo.setNombre(nombre);
        torneo.setDisciplina(Disciplina);
        torneo.setTipoTorneo(formato.elegirTorneo());
    }

    public void inscribirCompetidor(ParticipantePersona competidor) {
        torneo.getCompetidores().add(competidor);
    }

    public void inscribirEquipo(Equipo equipo) {
        torneo.getCompetidores().add(equipo);
    }

    //Deberían ser parte de la GUI
    public void generarCalendarioEnfrentamientos() {
    }

    public void generarBracketInicial() {
    }

    public void actualizarPosicionesBrackets() {
    }

    public void registrarResultadosEnfrentamientos() {
        actualizarPosicionesBrackets();
    }
}
