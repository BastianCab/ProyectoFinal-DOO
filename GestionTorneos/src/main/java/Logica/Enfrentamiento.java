package Logica;

import java.time.LocalDateTime;

/**
 * Clase que representa el enfrentamiento entre 2 competidores, contiene
 * una fecha y hora a la cual se enfrentarán, ambos participantes y el resultado una
 * vez ya realizado
 */
public class Enfrentamiento {
    private LocalDateTime fechaHora;
    private Participante Competidor1;
    private Participante Competidor2;
    private int resultado = -1;

    /**
     * Crea el enfrentamiento, la fecha y hora se debe especificar luego
     * @param Competidor1 el primer participante
     * @param Competidor2 el segundo participante
     */
    public Enfrentamiento(Participante Competidor1, Participante Competidor2) throws DatoInvalidoException {
        if (Competidor1 == null) {
            throw new DatoInvalidoException("El competidor 1 no puede ser nulo");
        }
        if (Competidor2 == null) {
            throw new DatoInvalidoException("El competidor 2 no puede ser nulo");
        }
        this.fechaHora = null;
        this.Competidor1 = Competidor1;
        this.Competidor2 = Competidor2;
    }

    /**
     * Getter del resultado una vez realizado el enfrentamiento
     * @return el resultado del enfrentamiento
     */
    public int getResultado() {
        return resultado;
    }

    /**
     * Setter del resultado
     * @param resultado el resultado del enfrentamiento, 1 si ganó el primer participante,
     *                  2 si ganó el segundo participante
     */
    public void setResultado(int resultado) throws DatoInvalidoException {
        if (resultado != 1 && resultado != 2) {
            throw new DatoInvalidoException("El tipo de resultado debe ser 1 o 2");
        }
        this.resultado = resultado;
    }

    /**
     * Getter de la fecha y la hora a la que se realizará o realizó el enfrentamiento
     * @return la fecha y hora del enfrentamiento
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Setter de la fecha y hora del enfrentamiento
     * @param fechaHora la fecha y hora a la cual se realizará el enfrentamiento
     */
    public void setFechaHora(LocalDateTime fechaHora) throws DatoInvalidoException {
        if (fechaHora == null) {
            throw new DatoInvalidoException("La fecha y hora del enfrentamiento no puede ser nula");
        }
        this.fechaHora = fechaHora;
    }

    /**
     * Getter del primer participante
     * @return el primer participante
     */
    public Participante getCompetidor1() {
        return Competidor1;
    }

    /**
     * Setter del primer participante
     * @param Competidor1 el participante al cual se quiere cambiar
     */
    public void setCompetidor1(Participante Competidor1) throws DatoInvalidoException {
        if (Competidor1 == null) {
            throw new DatoInvalidoException("El competidor 1 no puede ser nulo");
        }
        this.Competidor1 = Competidor1;
    }

    /**
     * Getter del segundo participante
     * @return el segundo participante
     */
    public Participante getCompetidor2() {
        return Competidor2;
    }

    /**
     * Setter del segundo participante
     * @param Competidor2 el participante al cual se quiere cambiar
     */
    public void setCompetidor2(Participante Competidor2) throws DatoInvalidoException {
        if (Competidor2 == null) {
            throw new DatoInvalidoException("El competidor 2 no puede ser nulo");
        }
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
        return "Enfrentamiento: " + Competidor1.getNombre() + " vs " +
                Competidor2.getNombre() + "\nEl " + fechaHora.toLocalDate()
                + " a las " + fechaHora.toLocalTime();
    }
}
