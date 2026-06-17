package Logica;

import java.time.LocalDate;

public class Organizador {
    public String nombre;
    public Organizador(String nombre) {
        this.nombre = nombre;
    }

    public void definirCaracteristicasTorneo() {
    }

    public void inscribirParticipantes(Participante competidor) {

    }

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
