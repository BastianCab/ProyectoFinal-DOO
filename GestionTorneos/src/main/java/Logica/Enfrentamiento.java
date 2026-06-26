package Logica;

import java.time.LocalTime;
import java.time.LocalDate;

public class Enfrentamiento {
    private LocalDate fecha;
    private LocalTime hora;
    private Participante Competidor1;
    private Participante Competidor2;
    private int resultado = -1;
    public Enfrentamiento(LocalDate fecha, LocalTime hora,
                          Participante Competidor1, Participante Competidor2) {
        this.fecha = fecha;
        this.hora = hora;
        this.Competidor1 = Competidor1;
        this.Competidor2 = Competidor2;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Participante getCompetidor1() {
        return Competidor1;
    }

    public void setCompetidor1(Participante Competidor1) {
        this.Competidor1 = Competidor1;
    }

    public Participante getCompetidor2() {
        return Competidor2;
    }

    public void setCompetidor2(Participante Competidor2) {
        this.Competidor2 = Competidor2;
    }

    @Override
    public String toString() {
        if (resultado == 1) {
            return Competidor1.getNombre() + " ganó el enfrentamiento contra " +
                    Competidor2.getNombre();
        } else if (resultado == 2) {
            return Competidor2.getNombre() + " ganó el enfrentamiento contra " +
                    Competidor1.getNombre();
        }
        return "El enfrentamiento no se ha realizado aún";
    }
}
